package com.project.shoppingmall.dto;

import com.project.shoppingmall.domain.Product;
import lombok.Data;

@Data
public class NewDTO {

    private Long productId;
    private String productName;
    private String productPrice;
    private String productCategory;
    private String productDescription;
    private String productMainName;
    private String productInfoName;
    private int orderCount;

    public NewDTO(Product product) {
        this(product.getProductId(), product.getProductName(), product.getProductPrice(), product.getProductCategory(),
                product.getProductDescription(), product.getProductMainName(), product.getProductInfoName(),
                product.getOrderCount());
    }

    public NewDTO(Long productId, String productName, String productPrice, String productCategory, String productDescription, String productMainName, String productInfoName, int orderCount) {
    }

    public NewDTO() {
    }
}
