package com.lary.springbootmall.service;

import com.lary.springbootmall.dto.CreateOrderRequest;
import com.lary.springbootmall.model.Order;

public interface OrderService {

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);


}
