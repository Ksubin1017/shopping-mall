package com.project.shoppingmall.controller;

import com.project.shoppingmall.domain.Orders;
import com.project.shoppingmall.dto.OrderDTO;
import com.project.shoppingmall.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    private final OrderService orderService;

    public AdminController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/adminOrderComp")
    public String adminOrderComp(Model model, @PageableDefault(page = 0, size = 10, sort = "orderCreatedAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Orders> orderComp = orderService.orderStatus("O", pageable);

        int nowPage = orderComp.getPageable().getPageNumber() + 1;  // 사용자에게 보여주기 위한 숫자
        int startPage = Math.max(nowPage - 2, 1);   // 페이징의 첫번째 목록
        int endPage = Math.min(startPage + 4, orderComp.getTotalPages());
        int previousPage = Math.max(nowPage - 2, 0);        //  -1 은 페이징 상의 현재, -2 페이징 상의 전 페이지
        int nextPage = Math.min(nowPage, orderComp.getTotalPages() - 1);   // 페이지 사
        int firstPage = 0;

        model.addAttribute("orderComp", orderComp);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("previousPage", previousPage);
        model.addAttribute("nextPage", nextPage);
        model.addAttribute("firstPage", firstPage);

        return "/adminOrderComp";
    }

    @GetMapping("/adminOrderNotComp")
    public String adminOrderNotComp(Model model, @PageableDefault(page = 0, size = 10, sort = "orderCreatedAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Orders> orderNotComp = orderService.orderStatus("X", pageable);

        int nowPage = orderNotComp.getPageable().getPageNumber() + 1;  // 사용자에게 보여주기 위한 숫자
        int startPage = Math.max(nowPage - 2, 1);   // 페이징의 첫번째 목록
        int endPage = Math.min(startPage + 4, orderNotComp.getTotalPages());
        int previousPage = Math.max(nowPage - 2, 0);        //  -1 은 페이징 상의 현재, -2 페이징 상의 전 페이지
        int nextPage = Math.min(nowPage, orderNotComp.getTotalPages() - 1);   // 페이지 사
        int firstPage = 0;

        model.addAttribute("orderNotComp", orderNotComp);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("previousPage", previousPage);
        model.addAttribute("nextPage", nextPage);
        model.addAttribute("firstPage", firstPage);

        return "/adminOrderNotComp";
    }

    @PostMapping("/orderComplete")
    public String orderComplete(@RequestParam("orderNum") String orderNum) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderStatus("O");
        orderService.updateOrderStatus(orderNum, orderDTO);
        return "/orderComplete";

    }
}
