package com.project.shoppingmall.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductDTO {

    public String productName;

    public String productPrice;

    public String productCategory;

    public String productDescription;

    public MultipartFile productMainFile;

    public MultipartFile productInfoFile;

}
