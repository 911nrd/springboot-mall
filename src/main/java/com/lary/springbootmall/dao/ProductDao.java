package com.lary.springbootmall.dao;

import com.lary.springbootmall.dto.ProductRequest;
import com.lary.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}
