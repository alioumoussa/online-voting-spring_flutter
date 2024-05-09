package com.spring.vote.helper;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.spring.vote.model.Candidat;


public class ExcelCandidatHelper {
  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  static String[] HEADERs = { "nom", "prenom", "adresse", "numeroIdentification", "numeroTelephone", "dateNaissance", "voted"};
  static String SHEET = "Candidats";

  public static boolean hasExcelFormat(MultipartFile file) {

    if (!TYPE.equals(file.getContentType())) {
      return false;
    }

    return true;
  }

  public static ByteArrayInputStream CandidatsToExcel(List<Candidat> Candidats) {

    try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
      Sheet sheet = workbook.createSheet(SHEET);

      // Header
      Row headerRow = sheet.createRow(0);

      for (int col = 0; col < HEADERs.length; col++) {
        Cell cell = headerRow.createCell(col);
        cell.setCellValue(HEADERs[col]);
      }

      int rowIdx = 1;
      for (Candidat Candidat : Candidats) {
        Row row = sheet.createRow(rowIdx++);
        Long idPartiPolitique = Candidat.getPartiPolitique().getId();

        row.createCell(0).setCellValue(Candidat.getNom());
        row.createCell(1).setCellValue(idPartiPolitique);
        row.createCell(2).setCellValue(Candidat.getPhotoUrl());
        row.createCell(3).setCellValue(Candidat.getDateNaissance());
       
      }

      workbook.write(out);
      return new ByteArrayInputStream(out.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
    }
  }

  public static List<Candidat> excelToCandidats(InputStream is) {
    try {
      Workbook workbook = new XSSFWorkbook(is);

      Sheet sheet = workbook.getSheet(SHEET);
      Iterator<Row> rows = sheet.iterator();

      List<Candidat> Candidats = new ArrayList<Candidat>();

      int rowNumber = 0;
      while (rows.hasNext()) {
        Row currentRow = rows.next();

        // skip header
        if (rowNumber == 0) {
          rowNumber++;
          continue;
        }

        Iterator<Cell> cellsInRow = currentRow.iterator();

        Candidat Candidat = new Candidat();

        int cellIdx = 0;
        while (cellsInRow.hasNext()) {
          Cell currentCell = cellsInRow.next();

          switch (cellIdx) {
          case 0:
            Candidat.setNom(currentCell.getStringCellValue());
            break;

          case 1:
            Candidat.setPartiPolitique(null);
            break;
            case 2:
            Candidat.setPhotoUrl(currentCell.getStringCellValue());
            break;
            case 3:
            Date date = currentCell.getDateCellValue();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Candidat.setDateNaissance(localDate);

            break;


          default:
            break;
          }

          cellIdx++;
        }

        Candidats.add(Candidat);
      }

      workbook.close();

      return Candidats;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
    }
  }
}