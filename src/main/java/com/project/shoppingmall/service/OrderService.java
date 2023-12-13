package com.project.shoppingmall.service;

import com.project.shoppingmall.domain.Orders;
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

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void saveOrder(Orders orders) {
        orderRepository.save(orders);
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
}
