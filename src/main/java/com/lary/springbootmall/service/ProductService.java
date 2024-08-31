package com.lary.springbootmall.service;

import com.lary.springbootmall.constant.ProductCategory;
import com.lary.springbootmall.dto.ProductRequest;
import com.lary.springbootmall.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts(ProductCategory category,String search);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);


}
