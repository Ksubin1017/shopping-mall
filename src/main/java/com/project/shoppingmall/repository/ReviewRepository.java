package com.project.shoppingmall.repository;

import com.project.shoppingmall.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {

    Page<Review> findByProductId(Long productId, Pageable pageable);
    List<Review> findByUserIdOrderByReviewCreatedAtDesc(String userId);

    void deleteByReviewNum(String reviewNum);
}
