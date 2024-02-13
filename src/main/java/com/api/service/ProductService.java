package com.api.service;

import com.api.entity.Product;
import com.api.helper.CsvImportHelper;
import com.api.helper.ExcelExportHelper;
import com.api.helper.ExcelImportHelper;
import com.api.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public void save(MultipartFile file) {

        try {
            List<Product> products = ExcelImportHelper.convertExcelToListOfProduct(file.getInputStream());
            this.productRepo.saveAll(products);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Product> getAllProducts() {
        return this.productRepo.findAll();
    }

    public ByteArrayInputStream getActualData() throws IOException {
        List<Product> all = productRepo.findAll();

        ByteArrayInputStream byteArrayInputStream = ExcelExportHelper.dataToExcel(all);
        return byteArrayInputStream;
    }

    public void importCSV(MultipartFile file) throws IOException {
        List<Product> list = CsvImportHelper.importFromCSV(file);
        productRepo.saveAll(list);
    }


}
