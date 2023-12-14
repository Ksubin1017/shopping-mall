package com.project.shoppingmall.service;

import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.dto.OrderCountDTO;
import com.project.shoppingmall.dto.ProductDTO;
import com.project.shoppingmall.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public Page<Product> outer(Pageable pageable) {

        return productRepository.findByProductCategory("outer", pageable);
    }

    public Page<Product> top(Pageable pageable) {

        return productRepository.findByProductCategory("top", pageable);
    }

    public Page<Product> pants(Pageable pageable) {

        return productRepository.findByProductCategory("pants", pageable);
    }

    public Page<Product> shoes(Pageable pageable) {

        return productRepository.findByProductCategory("shoes", pageable);
    }

    public Page<Product> acc(Pageable pageable) {

        return productRepository.findByProductCategory("acc", pageable);
    }

    public Product productDetails(Long productId) {

        return productRepository.findByProductId(productId);
    }

    public List<Product> searchByname(String keyword) {

        return productRepository.findByProductNameContaining(keyword);
    }

    @Transactional
    public void updateProductOrdered(Long productId, OrderCountDTO ordercountDTO) {
        Product product = productRepository.findByProductId(productId);
        product.updateProductOrdered(ordercountDTO.getOrderCount());
    }

    public List<Product> bestProduct() {
        return productRepository.findTop8ByOrderByOrderCountDesc();
    }

    public List<Product> newProduct() {
        return productRepository.findTop8ByOrderByProductCreadtedAtDesc();
    }
}
