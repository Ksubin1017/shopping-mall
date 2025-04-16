package com.project.shoppingmall.service;

import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.dto.BestNewDTO;
import com.project.shoppingmall.dto.OrderCountDTO;
import com.project.shoppingmall.dto.ProductDTO;
import com.project.shoppingmall.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

@Service
@Slf4j
public class ProductService {

    @Value("${upload.path}")
    private String uploadPath;

    private final ProductRepository productRepository;
    private final ReentrantLock lock = new ReentrantLock();

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
    public void updateProductOrdered(Long productId) {
        Product product = productRepository.findByProductIdWithLock(productId); // 락 걸림

        int currentCount = product.getOrderCount();
        product.updateProductOrdered(currentCount + 1); // 변경감지로 update됨
    }


    @Cacheable(value = "bestNew")
    public BestNewDTO bestNewProduct() {
        BestNewDTO bestNewDTO = new BestNewDTO();
        List<Product> bestProducts = productRepository.findTop8ByOrderByOrderCountDesc();
        List<Product> newProduct = productRepository.findTop8ByOrderByProductCreadtedAtDesc();

        bestNewDTO.setBestProducts(bestProducts);
        bestNewDTO.setNewProducts(newProduct);


        return bestNewDTO;
    }

}
