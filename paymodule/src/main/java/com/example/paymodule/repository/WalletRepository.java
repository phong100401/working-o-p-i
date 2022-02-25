package com.example.paymodule.repository;

import com.example.paymodule.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Integer>  {
    Wallet findWalletByUserId(int id);
}
