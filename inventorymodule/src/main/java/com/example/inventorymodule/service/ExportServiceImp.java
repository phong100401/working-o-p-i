package com.example.inventorymodule.service;

import com.example.inventorymodule.entity.ExportHistory;
import com.example.inventorymodule.repository.ExportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExportServiceImp implements ExportService{
    @Autowired
    ExportRepository exportRepository;

    @Override
    public ExportHistory creat(ExportHistory exportHistory) {
        return exportRepository.save(exportHistory);
    }
}
