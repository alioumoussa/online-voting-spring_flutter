package com.spring.vote.services;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class ExcelImportService<T> {
    private final Class<T> type;

    public ExcelImportService(Class<T> type) {
        this.type = type;
    }


    private static final String EXCEL_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static boolean hasExcelFormat(MultipartFile file) {
        return Objects.requireNonNull(file).getContentType().equals(EXCEL_TYPE);
    }

    public ByteArrayInputStream entitiesToExcel(List<T> entities) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Sheet");

            // Create header
            Row headerRow = sheet.createRow(0);
            Field[] fields = getEntityTypeFields();
            for (int i = 0; i < fields.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(fields[i].getName());
            }

            // Fill data
            int rowIdx = 1;
            for (T entity : entities) {
                Row row = sheet.createRow(rowIdx++);
                fillRowWithData(row, entity, fields);
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to export data to Excel: " + e.getMessage());
        }
    }

    public List<T> importEntitiesFromExcel(MultipartFile file) {
        List<T> entities = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            Field[] fields = getEntityTypeFields();
            Iterator<Row> rows = sheet.iterator();

            // Skip header
            if (rows.hasNext()) rows.next();

            while (rows.hasNext()) {
                Row row = rows.next();
                T entity = createEntityFromRow(row, fields);
                if (entity != null) {
                    entities.add(entity);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to import data from Excel: " + e.getMessage());
        }

        return entities;
    }

    @SuppressWarnings("unchecked")
    private Field[] getEntityTypeFields() {
        ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();
        return ((Class<T>) superclass.getActualTypeArguments()[0]).getDeclaredFields();
    }

    private void fillRowWithData(Row row, T entity, Field[] fields) {
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            Cell cell = row.createCell(i);
            try {
                Object value = fields[i].get(entity);
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                } else if (value instanceof LocalDate) {
                    cell.setCellValue(((LocalDate) value).toString());
                } else if (value instanceof Long || value instanceof Integer) {
                    cell.setCellValue((Long) value);
                } else if (value instanceof Boolean) {
                    cell.setCellValue((Boolean) value);
                } // Add more data types as needed
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private T createEntityFromRow(Row row, Field[] fields) {
        try {
            T entity = getEntityType().newInstance();
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                Object value = getCellValue(row.getCell(i), fields[i].getType());
                fields[i].set(entity, value);
            }
            return entity;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Class<T> getEntityType() {
        ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<T>) superclass.getActualTypeArguments()[0];
    }

    private Object getCellValue(Cell cell, Class<?> fieldType) {
        if (cell == null) return null;
        if (fieldType == String.class) {
            return cell.getStringCellValue();
        } else if (fieldType == LocalDate.class) {
            return cell.getLocalDateTimeCellValue().toLocalDate();
        } else if (fieldType == Long.class || fieldType == Integer.class) {
            return (long) cell.getNumericCellValue();
        } else if (fieldType == Boolean.class) {
            return cell.getBooleanCellValue();
        }
        return null;
    }
}
