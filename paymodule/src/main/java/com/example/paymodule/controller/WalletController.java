package com.example.paymodule.controller;

import com.example.paymodule.entity.TransactionHistory;
import com.example.paymodule.entity.Wallet;
import com.example.paymodule.repository.TransactionRepository;
import com.example.paymodule.repository.WalletRepository;
import com.example.paymodule.respone.RESTResponse;
import com.example.paymodule.service.WalletServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/payments")
public class WalletController {
    @Autowired
    WalletRepository walletRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    WalletServiceImp walletServiceImp;

    @RequestMapping(path = "account/{userId}", method = RequestMethod.GET)
    public ResponseEntity find(@PathVariable int userId) {
        userId = 451691;
        Wallet wallet = walletRepository.findWalletByUserId(userId);
        return new ResponseEntity<>(new RESTResponse.Success()
                .addData(wallet)
                .build(), HttpStatus.OK);
    }

    @RequestMapping(path = "transfer", method = RequestMethod.POST)
    public ResponseEntity send(@RequestBody TransactionHistory history) {
        return new ResponseEntity<>(new RESTResponse.Success()
                .addData(walletServiceImp.transfer(history))
                .build(), HttpStatus.OK);
    }

    @RequestMapping(path = "account/transaction", method = RequestMethod.GET)
    public ResponseEntity getTransaction(@RequestParam(name = "userId") int userId) {
        userId = 451691;
        return new ResponseEntity<>(new RESTResponse.Success()
                .addData(transactionRepository.findTransactionHistoryBySenderId(userId))
                .build(), HttpStatus.OK);
    }
    ;
}
