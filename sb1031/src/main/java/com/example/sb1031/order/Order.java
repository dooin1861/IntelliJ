package com.example.sb1031.order;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table (name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private int quantity;
    private double price;
    private Long orderId;

}
