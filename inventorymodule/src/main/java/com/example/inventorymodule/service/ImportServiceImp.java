package com.example.inventorymodule.service;

import com.example.inventorymodule.entity.ImportHistory;
import com.example.inventorymodule.entity.Product;
import com.example.inventorymodule.exception.NotFoundException;
import com.example.inventorymodule.repository.ImportRepository;
import com.example.inventorymodule.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ImportServiceImp implements ImportService{

    @Autowired
    ImportRepository importRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public ImportHistory save(ImportHistory importHistory) {
        Product product = productRepository.findById(importHistory.getProductId()).orElse(null);
        if (product == null) {
            throw new NotFoundException("Sản phẩm không tồn tại");
        }

        try {
            product.setCreatedAt(LocalDate.now());
            product.setUnitInStock(importHistory.getQuantity());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return importRepository.save(importHistory);
    }

}
