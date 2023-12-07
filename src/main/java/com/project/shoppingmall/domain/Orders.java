package com.project.shoppingmall.domain;

import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@Entity
@DynamicInsert
@Table(name="orders")
public class Orders {
    @Id
    private String orderNum;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String productPrice;

    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime orderCreatedAt;

    @ColumnDefault("O")
    private String orderStatus;

    protected Orders() {}

    public Orders(String orderNum, String userId, String userName, String address, Long productId, String productName, String productPrice) {
        this.orderNum = orderNum;
        this.userId = userId;
        this.userName = userName;
        this.address = address;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.orderCreatedAt = LocalDateTime.now();
    }
}
