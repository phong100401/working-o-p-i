package com.example.inventorymodule.service;

import com.example.inventorymodule.entity.ImportHistory;
import org.springframework.transaction.annotation.Transactional;

public interface ImportService {
    @Transactional
    ImportHistory save(ImportHistory importHistory);
}
