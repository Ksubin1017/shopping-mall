package com.project.shoppingmall.service;

import com.project.shoppingmall.domain.Orders;
import com.project.shoppingmall.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
       return orderRepository.findByUserId(userId);
    }
}
