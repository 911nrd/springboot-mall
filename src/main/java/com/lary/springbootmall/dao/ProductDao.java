package com.lary.springbootmall.dao;

import com.lary.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
}
