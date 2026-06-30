package com.project.shoppingmall.dto;

import com.project.shoppingmall.domain.Product;
import lombok.Data;

@Data
public class BestDTO {
    private Long productId;
    private String productName;
    private String productPrice;
    private String productCategory;
    private String productDescription;
    private String productMainName;
    private String productInfoName;
    private int orderCount;

    public BestDTO(Product product) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice();
        this.productCategory = product.getProductCategory();
        this.productDescription = product.getProductDescription();
        this.productMainName = product.getProductMainName();
        this.productInfoName = product.getProductInfoName();
        this.orderCount = product.getOrderCount();
    }

    public BestDTO() {
    }
}
