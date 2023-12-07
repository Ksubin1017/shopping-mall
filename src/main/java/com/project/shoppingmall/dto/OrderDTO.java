package com.project.shoppingmall.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {

    public String orderNum;

    public String userId;

    public String address;

    public Long productId;

    public String productName;

    public String productPrice;
}
