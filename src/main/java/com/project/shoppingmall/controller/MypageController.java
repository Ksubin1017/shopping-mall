package com.project.shoppingmall.controller;

import com.project.shoppingmall.domain.Orders;
import com.project.shoppingmall.domain.Review;
import com.project.shoppingmall.service.OrderService;
import com.project.shoppingmall.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MypageController {

    private final OrderService orderService;
    private final ReviewService reviewService;

    @Autowired
    public MypageController(OrderService orderService, ReviewService reviewService) {
        this.orderService = orderService;
        this.reviewService = reviewService;
    }

    @GetMapping("/myPage")
    public String myPage(Model model, HttpSession session) {
        String userId = (String)session.getAttribute("userId");

        List<Orders> ordersList = orderService.findOrder(userId);
        List<Review> reviewList = reviewService.findReviewUserId(userId);

        model.addAttribute("orders", ordersList);
        model.addAttribute("review", reviewList);
        return "/myPage";
    }

    @PostMapping("/reviewDel")
    public String reviewDel(@RequestParam("reviewNum") String reviewNum) {

        reviewService.reviewDel(reviewNum);
        return "/reviewDel";
    }
}
