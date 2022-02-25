package com.example.inventorymodule.repository;

import com.example.inventorymodule.entity.ImportHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportRepository extends JpaRepository<ImportHistory, Integer> {
}
