package com.project.shoppingmall.controller;

import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.dto.ProductDTO;
import com.project.shoppingmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class ProductController {

    @Value("${upload.path}")
    String uploadDir;

    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/best")
    public String best() {

        return "best";
    }

    @GetMapping("/newPage")
    public String newpage() {

        return "newPage";
    }

    @GetMapping("/outer")
    public String outer(Model model, @PageableDefault(page = 0, size=16, sort = "productCreadtedAt", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Product> outerList = productService.outer(pageable);

        int nowPage = outerList.getPageable().getPageNumber() + 1;  // 사용자에게 보여주기 위한 숫자
        int startPage = Math.max(nowPage - 2, 1);   // 페이징의 첫번째 목록
        int endPage = 0;   // 페이징의 마지막 목록
        int previousPage = Math.max(nowPage - 2, 0);        //  -1 은 페이징 상의 현재, -2 페이징 상의 전 페이지
        int nextPage = Math.min(nowPage, outerList.getTotalPages() - 1);   // 페이지 사
        int firstPage = 0;

        if(startPage < 2) {
            endPage = 5;
        } else {
            endPage = Math.min(nowPage + 2, outerList.getTotalPages());
        }

        model.addAttribute("outer", outerList);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("previousPage", previousPage);
        model.addAttribute("nextPage", nextPage);
        model.addAttribute("firstPage", firstPage);
        return "outer";
    }

    @GetMapping("/top")
    public String top(Model model, @PageableDefault(page = 0, size=16, sort = "productCreadtedAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Product> topList = productService.top(pageable);

        int nowPage = topList.getPageable().getPageNumber() + 1;  // 사용자에게 보여주기 위한 숫자
        int startPage = Math.max(nowPage - 2, 1);   // 페이징의 첫번째 목록
        int endPage = 0;   // 페이징의 마지막 목록
        int previousPage = Math.max(nowPage - 2, 0);        //  -1 은 페이징 상의 현재, -2 페이징 상의 전 페이지
        int nextPage = Math.min(nowPage, topList.getTotalPages() - 1);   // 페이지 사
        int firstPage = 0;

        if(startPage < 2) {
            endPage = 5;
        } else {
            endPage = Math.min(nowPage + 2, topList.getTotalPages());
        }

        model.addAttribute("top", topList);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("previousPage", previousPage);
        model.addAttribute("nextPage", nextPage);
        model.addAttribute("firstPage", firstPage);
        return "top";
    }

    @GetMapping("/pants")
    public String pants(Model model, @PageableDefault(page = 0, size=16, sort = "productCreadtedAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Product> pantsList = productService.pants(pageable);

        int nowPage = pantsList.getPageable().getPageNumber() + 1;  // 사용자에게 보여주기 위한 숫자
        int startPage = Math.max(nowPage - 2, 1);   // 페이징의 첫번째 목록
        int endPage = 0;   // 페이징의 마지막 목록
        int previousPage = Math.max(nowPage - 2, 0);        //  -1 은 페이징 상의 현재, -2 페이징 상의 전 페이지
        int nextPage = Math.min(nowPage, pantsList.getTotalPages() - 1);   // 페이지 사
        int firstPage = 0;

        if(startPage < 2) {
            endPage = 5;
        } else {
            endPage = Math.min(nowPage + 2, pantsList.getTotalPages());
        }

        model.addAttribute("pants", pantsList);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("previousPage", previousPage);
        model.addAttribute("nextPage", nextPage);
        model.addAttribute("firstPage", firstPage);
        return "pants";
    }

    @GetMapping("/shoes")
    public String shoes(Model model, @PageableDefault(page = 0, size=16, sort = "productCreadtedAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Product> shoesList = productService.shoes(pageable);

        int nowPage = shoesList.getPageable().getPageNumber() + 1;  // 사용자에게 보여주기 위한 숫자
        int startPage = Math.max(nowPage - 2, 1);   // 페이징의 첫번째 목록
        int endPage = 0;   // 페이징의 마지막 목록
        int previousPage = Math.max(nowPage - 2, 0);        //  -1 은 페이징 상의 현재, -2 페이징 상의 전 페이지
        int nextPage = Math.min(nowPage, shoesList.getTotalPages() - 1);   // 페이지 사
        int firstPage = 0;

        if(startPage < 2) {
            endPage = 5;
        } else {
            endPage = Math.min(nowPage + 2, shoesList.getTotalPages());
        }

        model.addAttribute("shoes", shoesList);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("previousPage", previousPage);
        model.addAttribute("nextPage", nextPage);
        model.addAttribute("firstPage", firstPage);
        return "shoes";
    }

    @GetMapping("/acc")
    public String acc(Model model, @PageableDefault(page = 0, size=16, sort = "productCreadtedAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Product> accList = productService.acc(pageable);

        int nowPage = accList.getPageable().getPageNumber() + 1;  // 사용자에게 보여주기 위한 숫자
        int startPage = Math.max(nowPage - 2, 1);   // 페이징의 첫번째 목록
        int endPage = 0;   // 페이징의 마지막 목록
        int previousPage = Math.max(nowPage - 2, 0);        //  -1 은 페이징 상의 현재, -2 페이징 상의 전 페이지
        int nextPage = Math.min(nowPage, accList.getTotalPages() - 1);   // 페이지 사
        int firstPage = 0;

        if(startPage < 2) {
            endPage = 5;
        } else {
            endPage = Math.min(nowPage + 2, accList.getTotalPages());
        }

        model.addAttribute("acc", accList);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("previousPage", previousPage);
        model.addAttribute("nextPage", nextPage);
        model.addAttribute("firstPage", firstPage);
        return "acc";
    }

    @GetMapping("/productEnroll")
    public String product() {

        return "productEnroll";
    }

    //상품 등록
    @PostMapping("/productEnroll")
    public String productEnroll(ProductDTO productDTO) throws IOException {
        String productMainFileName = productService.saveMainFileName(productDTO);
        String productInfoFileName = productService.saveInfoFileName(productDTO);

        Product product = new Product(productDTO.getProductName(), productDTO.getProductPrice(), productDTO.getProductCategory(), productDTO.getProductDescription(), productMainFileName, productInfoFileName);

        productService.saveProduct(product);
        return "main";
    }
}
