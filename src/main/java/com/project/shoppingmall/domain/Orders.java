package com.project.shoppingmall.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@ToString
@Entity
@Table(name="orders")
public class Orders {
    @Id
    private String orderNum;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String postcode;

    @Column(nullable = false)
    private String address1;

    @Column(nullable = false)
    private String address2;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String productPrice;

    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
    @Column(updatable = false)
    @CreatedDate
    private LocalDate orderCreatedAt;

    private String orderStatus;

    protected Orders() {}

    public Orders(String orderNum, String userId, String userName, String postcode, String address1, String address2, Long productId, String productName, String productPrice) {
        this.orderNum = orderNum;
        this.userId = userId;
        this.userName = userName;
        this.postcode = postcode;
        this.address1 = address1;
        this.address2 = address2;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.orderCreatedAt = LocalDate.now();
        this.orderStatus = "X";
    }

    public void updateOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}