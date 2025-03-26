package com.project.shoppingmall.dto;

import com.project.shoppingmall.domain.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BestNewDTO {

    List<Product> bestProducts;
    List<Product> newProducts;
}
