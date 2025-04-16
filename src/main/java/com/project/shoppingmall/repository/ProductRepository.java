package com.project.shoppingmall.repository;

import com.project.shoppingmall.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByProductCategory(String productCategory, Pageable pageable);
    Product findByProductId(Long productId);
    List<Product> findByProductNameContaining(String keyword);
    List<Product> findTop8ByOrderByOrderCountDesc();
    List<Product> findTop8ByOrderByProductCreadtedAtDesc();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Product p WHERE p.productId = :productId")
    Product findByProductIdWithLock(Long productId);
}
