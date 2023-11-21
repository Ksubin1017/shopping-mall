package com.project.shoppingmall.controller;

import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.dto.ProductDTO;
import com.project.shoppingmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {

    @Value("${upload.path}")
    String uploadDir;

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/best")
    public String best() {

        return "/best";
    }

    @GetMapping("/newPage")
    public String newpage() {

        return "/newPage";
    }

    @GetMapping("/outer")
    public String outer(Model model) {
        List<Product> outer = productService.outer();
        model.addAttribute("outer", outer);
        return "/outer";
    }

    @GetMapping("/top")
    public String top(Model model) {
        List<Product> top = productService.top();
        model.addAttribute("top", top);
        return "/top";
    }

    @GetMapping("/pants")
    public String pants(Model model) {
        List<Product> pants = productService.pants();
        model.addAttribute("pants", pants);
        return "/pants";
    }

    @GetMapping("/shoes")
    public String shoes(Model model) {
        List<Product> shoes = productService.shoes();
        model.addAttribute("shoes", shoes);
        return "/shoes";
    }

    @GetMapping("/acc")
    public String acc(Model model) {
        List<Product> acc = productService.acc();
        model.addAttribute("acc", acc);
        return "/acc";
    }

    @GetMapping("/productEnroll")
    public String product() {

        return "/productEnroll";
    }

    //상품 등록
    @PostMapping("/productEnroll")
    public String productEnroll(ProductDTO productDTO) throws IOException {
        String productMainFileName = productService.saveMainFileName(productDTO);
        String productInfoFileName = productService.saveInfoFileName(productDTO);

        Product product = new Product(productDTO.getProductName(), productDTO.getProductPrice(), productDTO.getProductCategory(), productDTO.getProductDescription(), productMainFileName, productInfoFileName);

        productService.saveProduct(product);
        return "/main";
    }
}
