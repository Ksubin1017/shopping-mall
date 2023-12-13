package com.project.shoppingmall.repository;

import com.project.shoppingmall.domain.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, String> {

    List<Orders> findByUserIdOrderByOrderCreatedAtDesc(String userId);
    Page<Orders> findByOrderStatus(String orderStatus, Pageable pageable);
    Orders findByOrderNum(String OrderNum);

}
