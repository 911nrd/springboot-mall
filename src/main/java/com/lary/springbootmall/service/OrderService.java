package com.lary.springbootmall.service;

import com.lary.springbootmall.dto.CreateOrderRequest;
import com.lary.springbootmall.dto.OrderQueryParams;
import com.lary.springbootmall.model.Order;

import java.util.List;

public interface OrderService {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);


}
