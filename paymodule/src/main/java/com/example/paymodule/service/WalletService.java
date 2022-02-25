package com.example.paymodule.service;

import com.example.paymodule.dto.TransactionDto;
import com.example.paymodule.entity.TransactionHistory;
import com.example.paymodule.entity.Wallet;

import javax.transaction.Transactional;

public interface WalletService {
    Wallet save(Wallet wallet);

    @Transactional
    TransactionDto transfer(TransactionHistory history);

    Wallet findWalletByUserId(int userId);
}
