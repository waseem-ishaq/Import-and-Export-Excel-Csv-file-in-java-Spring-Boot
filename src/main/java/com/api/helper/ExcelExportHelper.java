package com.api.helper;

import com.api.entity.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExportHelper {

    public static String[] HEADERS={
            "productId",
            "productName",
            "productDesc",
            "productPrice"
    };

    public static String SHEET_NAME="data";

    public static ByteArrayInputStream dataToExcel(List<Product> list) throws IOException {

        Workbook workbook=new XSSFWorkbook();
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        try {

            Sheet sheet=workbook.createSheet(SHEET_NAME);

            //Create Row Headers
            Row row=sheet.createRow(0);

            for (int i=0;i<HEADERS.length;i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(HEADERS[i]);
            }

            int rowIndex=1;
            for (Product excelData:list){
                Row dataRow = sheet.createRow(rowIndex);
                rowIndex++;
                dataRow.createCell(0).setCellValue(excelData.getProductId());
                dataRow.createCell(1).setCellValue(excelData.getProductName());
                dataRow.createCell(2).setCellValue(excelData.getProductDesc());
                dataRow.createCell(3).setCellValue(excelData.getProductPrice());
            }
            workbook.write(out);

            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in exporting");
        }finally {
            workbook.close();
            out.close();
        }

        return null;
    }
}
