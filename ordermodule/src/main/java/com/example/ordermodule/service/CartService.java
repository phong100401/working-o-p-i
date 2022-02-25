package com.example.ordermodule.service;

import com.example.ordermodule.entity.CartItem;

import java.util.HashMap;

public interface CartService {
    HashMap<Integer, CartItem> addToCart(CartItem cartItem1);

    void clear();

    HashMap<Integer, CartItem> getDetail();

    HashMap<Integer, CartItem> updateCart(int productId, int quantity);
}
