package com.api.helper;


import com.api.entity.Product;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvImportHelper {

    public static List<Product> importFromCSV(MultipartFile file) throws IOException {
        List<Product> dataList = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] line;
            // Skip header row
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                Product data = new Product();
                data.setProductId(Integer.parseInt(line[0]));
                data.setProductName(line[1]);
                data.setProductDesc(line[2]);
                data.setProductPrice(Double.parseDouble(line[3]));
                dataList.add(data);
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return dataList;
    }
}
