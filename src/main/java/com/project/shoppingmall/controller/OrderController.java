package com.project.shoppingmall.controller;

import com.project.shoppingmall.domain.Orders;
import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.domain.Users;
import com.project.shoppingmall.dto.OrderCountDTO;
import com.project.shoppingmall.service.OrderService;
import com.project.shoppingmall.service.ProductService;
import com.project.shoppingmall.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final UsersService usersService;
    private final ProductService productService;

    public OrderController(OrderService orderService, UsersService usersService, ProductService productService) {
        this.orderService = orderService;
        this.usersService = usersService;
        this.productService = productService;
    }

    @GetMapping("/payment")
    public String payment(@RequestParam("productId") Long productId, HttpSession session, Model model) {
        String userId = (String)session.getAttribute("userId");

        if(userId == null || userId.equals("anonymousUser")) {

            return "loginForm";
        } else {
            Users users = usersService.findUser(userId);
            Product product = productService.productDetails(productId);
            model.addAttribute("product", product);
            model.addAttribute("users", users);
            return "payment";
        }
    }

    @GetMapping("/paymentend")
    public String paymentComp(Model model, HttpSession session) {
        Long productId = (Long) session.getAttribute("productId");
        String userId = (String)session.getAttribute("userId");
        Users users = usersService.findUser(userId);
        Product product = productService.productDetails(productId);
        model.addAttribute("users", users);
        model.addAttribute("product", product);

        return "paymentend";
    }

    @PostMapping("/paymentend")
    public String paymentEnd(@RequestParam("orderNum") String orderNum,
                             @RequestParam("userId") String userId,
                             @RequestParam("productId") Long productId,
                             HttpSession session) {
        orderService.saveOrder(userId, productId, orderNum);

        session.setAttribute("productId", productId);

        return "redirect:paymentend";
    }
}
