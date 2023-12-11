package com.project.shoppingmall.controller;

import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.domain.Review;
import com.project.shoppingmall.service.ProductService;
import com.project.shoppingmall.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductDetailsController {

    private final ProductService productService;
    private final ReviewService reviewService;

    @Autowired
    public ProductDetailsController(ProductService productService, ReviewService reviewService) {
        this.productService = productService;
        this.reviewService = reviewService;
    }

    @GetMapping("/details")
    public String productDetails(@RequestParam("productId") Long productId, Model model, @PageableDefault(page = 0, size=5, sort = "reviewCreatedAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Product product = productService.productDetails(productId);
        Page<Review> review = reviewService.findReview(productId, pageable);

        int nowPage = review.getPageable().getPageNumber() + 1;  // 사용자에게 보여주기 위한 숫자
        int startPage = Math.max(nowPage - 2, 1);   // 페이징의 첫번째 목록
        int endPage = Math.min(startPage + 4, review.getTotalPages());
        int previousPage = Math.max(nowPage - 2, 0);        //  -1 은 페이징 상의 현재, -2 페이징 상의 전 페이지
        int nextPage = Math.min(nowPage, review.getTotalPages() - 1);   // 페이지 사
        int firstPage = 0;

        model.addAttribute("review", review);
        model.addAttribute("product", product);
        model.addAttribute("productId", productId);
        model.addAttribute("review", review);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("previousPage", previousPage);
        model.addAttribute("nextPage", nextPage);
        model.addAttribute("firstPage", firstPage);
        return "/details";
    }

}
