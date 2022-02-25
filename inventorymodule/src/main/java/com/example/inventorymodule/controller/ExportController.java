package com.example.inventorymodule.controller;

import com.example.inventorymodule.entity.ExportHistory;
import com.example.inventorymodule.response.RESTResponse;
import com.example.inventorymodule.service.ExportServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/export/history")
public class ExportController {
    @Autowired
    ExportServiceImp exportServiceImp;

    @RequestMapping(path = "create", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody ExportHistory exportHistory){
        return new ResponseEntity(new RESTResponse.Success()
                .addData(exportServiceImp.creat(exportHistory))
                .build(), HttpStatus.OK);
    }
}
