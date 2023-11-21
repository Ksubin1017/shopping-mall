package com.project.shoppingmall.service;

import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.dto.ProductDTO;
import com.project.shoppingmall.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Value("${upload.path}")
    private String uploadPath;

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public String saveMainFileName(ProductDTO productDTO) throws IOException {

        MultipartFile productMainFile = productDTO.getProductMainFile();

        String originalMainFileName = productMainFile.getOriginalFilename();
        String storedMainFileName = UUID.randomUUID() + "_" + originalMainFileName;
        String saveMainPath = uploadPath + storedMainFileName;

        productMainFile.transferTo(new File(saveMainPath));

        return storedMainFileName;
    }

    public String saveInfoFileName(ProductDTO productDTO) throws IOException {

        MultipartFile productInfoFile = productDTO.getProductInfoFile();

        String originalInfoFileName = productInfoFile.getOriginalFilename();
        String storedInfoFileName = UUID.randomUUID() + "_" + originalInfoFileName;
        String saveInfoPath = uploadPath + storedInfoFileName;

        productInfoFile.transferTo(new File(saveInfoPath));

        return storedInfoFileName;
    }

    public List<Product> outer() {
        List<Product> outer = productRepository.findByProductCategory("outer");

        return outer;
    }

    public List<Product> top() {
        List<Product> top = productRepository.findByProductCategory("top");

        return top;
    }

    public List<Product> pants() {
        List<Product> pants = productRepository.findByProductCategory("pants");

        return pants;
    }

    public List<Product> shoes() {
        List<Product> shoes = productRepository.findByProductCategory("shoes");

        return shoes;
    }

    public List<Product> acc() {
        List<Product> acc = productRepository.findByProductCategory("acc");

        return acc;
    }
}
