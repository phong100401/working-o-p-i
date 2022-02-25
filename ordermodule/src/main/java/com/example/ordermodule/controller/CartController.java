package com.example.ordermodule.controller;

import com.example.ordermodule.entity.CartItem;
import com.example.ordermodule.repository.ProductRepository;
import com.example.ordermodule.respone.RESTResponse;
import com.example.ordermodule.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/cart")
public class CartController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartService cartService;

    @RequestMapping(method = RequestMethod.POST, path = "add")
    public ResponseEntity addToCart(@RequestBody CartItem cartItem) {
        return new ResponseEntity<>(new RESTResponse.Success()
                .addData(cartService.addToCart(cartItem))
                .build(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "clear")
    public ResponseEntity clear() {
        cartService.clear();
        return new ResponseEntity<>(new RESTResponse.Success()
                .build(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "detail")
    public ResponseEntity getDetail() {
        return new ResponseEntity<>(new RESTResponse.Success()
                .addData(cartService.getDetail())
                .build(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "update")
    public ResponseEntity update(@RequestParam(name = "productId") int productId,
                                 @RequestParam(name = "quantity") int quantity) {
        return new ResponseEntity<>(new RESTResponse.Success()
                .addData(cartService.updateCart(productId, quantity))
                .build(), HttpStatus.OK);
    }

}
