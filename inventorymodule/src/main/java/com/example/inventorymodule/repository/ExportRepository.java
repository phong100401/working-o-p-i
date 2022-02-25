package com.example.inventorymodule.repository;

import com.example.inventorymodule.entity.ExportHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExportRepository extends JpaRepository<ExportHistory, Integer> {
}
