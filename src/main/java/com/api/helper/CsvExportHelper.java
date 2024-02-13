package com.api.helper;


import com.api.entity.Product;
import com.opencsv.CSVWriter;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CsvExportHelper {

    public static void exportToCSV(HttpServletResponse response, List<Product> data) throws IOException {
        String filename = "DB_CSV-data.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");

        try (CSVWriter csvWriter = new CSVWriter(response.getWriter())) {
            // Write header row
            csvWriter.writeNext(new String[]{"productId", "productName", "productDesc", "productPrice"});

            // Write data rows
            for (Product rowData : data) {
                csvWriter.writeNext(new String[]{
                        String.valueOf(rowData.getProductId()),
                        rowData.getProductName(),
                        rowData.getProductDesc(),
                        String.valueOf(rowData.getProductPrice())
                });
            }
        }
    }
}

