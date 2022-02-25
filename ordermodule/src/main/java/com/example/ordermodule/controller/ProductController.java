package com.example.ordermodule.controller;

import com.example.ordermodule.repository.ProductRepository;
import com.example.ordermodule.respone.RESTPagination;
import com.example.ordermodule.respone.RESTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    ProductRepository productRepository;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                 @RequestParam(name = "pageSize", defaultValue = "9") int pageSize
    ){
        if (page <= 0 ){
            page = 1;
        }
        if (pageSize < 0){
            page = 9;
        }

        Page paging = productRepository.findAll(PageRequest.of(page - 1, pageSize));
        return new ResponseEntity<>(new RESTResponse.Success()
                .setPagination(new RESTPagination(paging.getNumber() + 1, paging.getSize(), paging.getTotalElements()))
                .addData(paging.getContent())
                .buildData(), HttpStatus.OK);
    }
}
