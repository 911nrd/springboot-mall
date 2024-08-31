package com.lary.springbootmall.service;

import com.lary.springbootmall.dto.ProductRequest;
import com.lary.springbootmall.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
