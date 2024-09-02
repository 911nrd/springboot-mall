package com.lary.springbootmall.dao;

import com.lary.springbootmall.dto.ProductQueryParams;
import com.lary.springbootmall.dto.ProductRequest;
import com.lary.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {
    Integer countProduct(ProductQueryParams productQueryParams);

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
