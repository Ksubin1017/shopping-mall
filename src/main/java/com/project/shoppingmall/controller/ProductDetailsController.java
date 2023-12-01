package com.project.shoppingmall.controller;

import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductDetailsController {

    private final ProductService productService;

    @Autowired
    public ProductDetailsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/details")
    public String productDetails(@RequestParam("productId") Long productId, Model model) {
        Product product = productService.productDetails(productId);
        model.addAttribute("product", product);
        return "/details";
    }

}
