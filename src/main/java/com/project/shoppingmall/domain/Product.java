package com.project.shoppingmall.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@Entity
@Table(name="product")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String productPrice;

    @Column(nullable = false)
    private String productCategory;

    private String productDescription;

    @Column(nullable = false)
    private String productMainName;

    private String productInfoName;

    private int orderCount;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime productCreadtedAt;

    public Product() {}

    public Product(String productName, String productPrice, String productCategory, String productDescription, String productMainName, String productInfoName) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
        this.productDescription = productDescription;
        this.productMainName = productMainName;
        this.productInfoName = productInfoName;
        this.productCreadtedAt = LocalDateTime.now();
        this.orderCount = 0;
    }

    public void updateProductOrdered(int orderCount) {
        this.orderCount = orderCount;
    }
}