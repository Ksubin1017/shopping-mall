package com.project.shoppingmall.controller;

import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    private final ProductService productService;

    public SearchController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/search")
    public String searchProduct(Model model,
                                @RequestParam("keyword")String keyword) {

        List<Product> productList = productService.searchByname(keyword);
        model.addAttribute("productList", productList);
        model.addAttribute("keyword", keyword);
        return "/search";
    }



}
