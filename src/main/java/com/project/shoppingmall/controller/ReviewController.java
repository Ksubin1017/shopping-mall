package com.project.shoppingmall.controller;

import com.project.shoppingmall.domain.Orders;
import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.domain.Review;
import com.project.shoppingmall.service.OrderService;
import com.project.shoppingmall.service.ProductService;
import com.project.shoppingmall.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class ReviewController {

    private final ReviewService reviewService;
    private final OrderService orderService;
    private final ProductService productService;

    @Autowired
    public ReviewController(ReviewService reviewService, OrderService orderService, ProductService productService) {
        this.reviewService = reviewService;
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping("/review")
    public String writeReviewForm(Model model, @RequestParam("productId") Long productId) {
        Product product = productService.productDetails(productId);
        model.addAttribute("product", product);
        return "/review";
    }

    @PostMapping("/writeReview")
    public String writeReview(HttpSession session,
                            @RequestParam("productId") Long productId,
                            @RequestParam("content") String content) throws IOException {
        String userId = (String)session.getAttribute("userId");

        List<Orders> ordersList = orderService.findOrder(userId);
        Orders foundOrder = ordersList.stream()
                .filter(orders -> orders.getProductId().equals(productId))
                .findFirst()
                .orElse(null);

        if(foundOrder != null) {
            Review review = new Review(userId, productId, content);
            reviewService.saveReview(review);

            return "/reviewComp";
        }

        return "/reviewError";
    }

}
