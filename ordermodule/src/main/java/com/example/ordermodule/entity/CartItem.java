package com.example.ordermodule.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartItem {
    private int productId;
    private String name;
    private int quantity;
    private double unitPrice;
}
