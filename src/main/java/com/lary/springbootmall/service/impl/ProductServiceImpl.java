package com.lary.springbootmall.service.impl;

import com.lary.springbootmall.dao.ProductDao;
import com.lary.springbootmall.model.Product;
import com.lary.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}
