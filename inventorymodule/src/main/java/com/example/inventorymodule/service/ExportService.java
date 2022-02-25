package com.example.inventorymodule.service;

import com.example.inventorymodule.entity.ExportHistory;
import org.springframework.transaction.annotation.Transactional;

public interface ExportService {
    @Transactional
    ExportHistory creat(ExportHistory exportHistory);
}
