package com.project.shoppingmall.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@ToString
@Entity
@Table(name= "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewNum;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(updatable = false)
    @CreatedDate
    private LocalDate reviewCreatedAt;

    protected Review() {}

    public Review(String userId, Long productId, String content) {
        this.userId = userId;
        this.productId = productId;
        this.content = content;
        this.reviewCreatedAt = LocalDate.now();
    }

}