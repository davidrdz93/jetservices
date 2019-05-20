package com.davidrdz93.jetservices.services;

import com.davidrdz93.jetservices.entities.RateCorsi;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.swing.text.DateFormatter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service(value = "xlsxDebitoriMainImp")
public class XlsxDebitoriGeneratorImp implements XlsxDebitoriGenerator
{
    private String[] intestazioni = {"Nome studente", "Importo rata", "Data scadenza", "Nome classe"};

    @Override
    public ByteArrayInputStream debitoriToExcel(List<RateCorsi> rateCorsi) throws IOException
    {

        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        )
        {
            Sheet sheet = workbook.createSheet("Elenco rate scadute");
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);

            int rowNum = 0;

            Row headerRow = sheet.createRow(rowNum++);


            // scrivo intestazione
            for (int colNum = 0; colNum < intestazioni.length; colNum++)
            {
                Cell cell = headerRow.createCell(colNum);
                cell.setCellValue(intestazioni[colNum]);
            }

            // scrivo il contenuto
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            for (RateCorsi rataCorsi : rateCorsi)
            {
                Row row = sheet.createRow(rowNum++);
                String nomeCompleto = rataCorsi.getIscrizione().getStudente().getNome() + " " +
                        rataCorsi.getIscrizione().getStudente().getCognome();

                String dataScadenzaStr = dateFormat.format(rataCorsi.getDataScadenza());

                row.createCell(0).setCellValue(nomeCompleto);
                row.createCell(1).setCellValue(rataCorsi.getImporto());
                row.createCell(2).setCellValue(dataScadenzaStr);
                row.createCell(3).setCellValue(rataCorsi.getIscrizione().getCorso().getNome());
            }

            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        }
    }
}
