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

import com.spring.vote.model.Electeur;


public class ExcelElecteurHelper {
  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  static String[] HEADERs = { "nom", "prenom", "adresse", "numeroIdentification", "numeroTelephone", "dateNaissance", "voted"};
  static String SHEET = "Electeurs";

  public static boolean hasExcelFormat(MultipartFile file) {

    if (!TYPE.equals(file.getContentType())) {
      return false;
    }

    return true;
  }

  public static ByteArrayInputStream ElecteursToExcel(List<Electeur> Electeurs) {

    try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
      Sheet sheet = workbook.createSheet(SHEET);

      // Header
      Row headerRow = sheet.createRow(0);

      for (int col = 0; col < HEADERs.length; col++) {
        Cell cell = headerRow.createCell(col);
        cell.setCellValue(HEADERs[col]);
      }

      int rowIdx = 1;
      for (Electeur Electeur : Electeurs) {
        Row row = sheet.createRow(rowIdx++);

        row.createCell(0).setCellValue(Electeur.getNom());
        row.createCell(1).setCellValue(Electeur.getPrenom());
        row.createCell(2).setCellValue(Electeur.getAdresse());
        row.createCell(3).setCellValue(Electeur.getNumeroIdentification());
        row.createCell(4).setCellValue(Electeur.getNumeroTelephone());
        row.createCell(5).setCellValue(Electeur.getDateNaissance());
        row.createCell(3).setCellValue(Electeur.getVoted());
      }

      workbook.write(out);
      return new ByteArrayInputStream(out.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
    }
  }

  public static List<Electeur> excelToElecteurs(InputStream is) {
    try {
      Workbook workbook = new XSSFWorkbook(is);

      Sheet sheet = workbook.getSheet(SHEET);
      Iterator<Row> rows = sheet.iterator();

      List<Electeur> Electeurs = new ArrayList<Electeur>();

      int rowNumber = 0;
      while (rows.hasNext()) {
        Row currentRow = rows.next();

        // skip header
        if (rowNumber == 0) {
          rowNumber++;
          continue;
        }

        Iterator<Cell> cellsInRow = currentRow.iterator();

        Electeur Electeur = new Electeur();

        int cellIdx = 0;
        while (cellsInRow.hasNext()) {
          Cell currentCell = cellsInRow.next();

          switch (cellIdx) {
          case 0:
            Electeur.setNom(currentCell.getStringCellValue());
            break;

          case 1:
            Electeur.setPrenom(currentCell.getStringCellValue());
            break;
            case 2:
            Electeur.setAdresse(currentCell.getStringCellValue());
            break;
            case 3:
            Electeur.setNumeroIdentification(currentCell.getStringCellValue());
            break;
            case 4:
            Electeur.setNumeroTelephone(currentCell.getStringCellValue());
            break;
            case 5:
            Date date = currentCell.getDateCellValue();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Electeur.setDateNaissance(localDate);

            break;
            case 6:
                String votedString = currentCell.getStringCellValue();
                boolean votedBoolean = Boolean.parseBoolean(votedString);
                Electeur.setVoted(votedBoolean);
                break;

          default:
            break;
          }

          cellIdx++;
        }

        Electeurs.add(Electeur);
      }

      workbook.close();

      return Electeurs;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
    }
  }
}