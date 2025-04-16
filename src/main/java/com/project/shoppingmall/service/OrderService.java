package com.project.shoppingmall.service;

import com.project.shoppingmall.domain.Orders;
import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.domain.Users;
import com.project.shoppingmall.dto.OrderCountDTO;
import com.project.shoppingmall.dto.OrderDTO;
import com.project.shoppingmall.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UsersService usersService;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository, UsersService usersService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.usersService = usersService;
        this.productService = productService;
    }



    public List<Orders> findOrder(String userId) {
       return orderRepository.findByUserIdOrderByOrderCreatedAtDesc(userId);
    }

    public Page<Orders> orderStatus(String orderStatus, Pageable pageable) {
        return orderRepository.findByOrderStatus(orderStatus, pageable);
    }

    @Transactional
    public void updateOrderStatus(String orderNum, OrderDTO orderDTO) {
        Orders orders = orderRepository.findByOrderNum(orderNum);
        orders.updateOrderStatus(orderDTO.getOrderStatus());
    }

    public void saveOrder(String userId, Long productId, String orderNum) {
        Users users = usersService.findUser(userId);
        Product product = productService.productDetails(productId);
        Orders orders = new Orders(orderNum, users.getUserId(), users.getName(), users.getPostcode(), users.getAddress1(), users.getAddress2(), product.getProductId(), product.getProductName(), product.getProductPrice());
        OrderCountDTO orderCountDTO = new OrderCountDTO();
        orderCountDTO.setOrderCount(product.getOrderCount() + 1);
        productService.updateProductOrdered(productId);


        orderRepository.save(orders);
    }

//    public void saveOrder(String userId, Long productId, String orderNum) {
//        Users users = usersService.findUser(userId);
//        Product product = productService.productDetails(productId);
//
//        Orders orders = new Orders(orderNum, users.getUserId(), users.getName(),
//                users.getPostcode(), users.getAddress1(), users.getAddress2(),
//                product.getProductId(), product.getProductName(), product.getProductPrice());
//
//        synchronized (product) {  // 🚨 특정 상품(productId) 단위로 동기화
//            OrderCountDTO orderCountDTO = new OrderCountDTO();
//            orderCountDTO.setOrderCount(product.getOrderCount() + 1);
//            productService.updateProductOrdered(productId, orderCountDTO);
//        }
//
//        orderRepository.save(orders);
//    }

}
