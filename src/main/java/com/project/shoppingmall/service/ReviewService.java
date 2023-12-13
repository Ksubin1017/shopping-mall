package com.project.shoppingmall.service;

import com.project.shoppingmall.domain.Review;
import com.project.shoppingmall.dto.ReviewDTO;
import com.project.shoppingmall.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void saveReview(Review review) {
        reviewRepository.save(review);
    }

    public Page<Review> findReview(Long productId, Pageable pageable) {
        return reviewRepository.findByProductId(productId, pageable);
    }

    public List<Review> findReviewUserId(String userId) {
        return reviewRepository.findByUserIdOrderByReviewCreatedAtDesc(userId);
    }

    @Transactional
    public void reviewDel(String reviewNum) {
        reviewRepository.deleteByReviewNum(reviewNum);
    }
}
