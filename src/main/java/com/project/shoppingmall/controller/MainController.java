package com.project.shoppingmall.controller;

import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.dto.BestNewDTO;
import com.project.shoppingmall.service.ProductService;
import com.project.shoppingmall.service.UsersService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MainController {

    private final ProductService productService;
    private final UsersService usersService;

    public MainController(ProductService productService, UsersService usersService) {
        this.productService = productService;
        this.usersService = usersService;
    }

    @GetMapping("/")
    public String main(HttpSession session, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        session.setAttribute("userId", userId);

//        List<Product> bestProduct = productService.bestProduct();
//        List<Product> newProduct = productService.newProduct();

        BestNewDTO bestNewDTO = productService.bestNewProduct();
        model.addAttribute("bestNewProduct", bestNewDTO);

        return "main";
    }
}
