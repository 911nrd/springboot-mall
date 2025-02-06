package com.lary.springbootmall.service.impl;
import com.lary.springbootmall.dao.ProductDao;
import com.lary.springbootmall.dao.OrderDao;
import com.lary.springbootmall.dao.UserDao;

import com.lary.springbootmall.dto.BuyItem;
import com.lary.springbootmall.dto.CreateOrderRequest;
import com.lary.springbootmall.dto.OrderQueryParams;
import com.lary.springbootmall.model.Order;
import com.lary.springbootmall.model.OrderItem;
import com.lary.springbootmall.model.Product;
import com.lary.springbootmall.model.User;
import com.lary.springbootmall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        return orderDao.countOrder(orderQueryParams);
    }

    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {
        List<Order> orderList = orderDao.getOrders(orderQueryParams);
        for (Order order:orderList)
        {
            List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(order.getOrderId());
            order.setOrderItemList(orderItemList);
        }
        return orderList;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);
        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);

        order.setOrderItemList(orderItemList);
        return order;
    }

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        //檢查user是否存在
        User user  = userDao.getUserById(userId);
        if (user == null)
        {
            log.warn("該user不存在",userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();
        for (BuyItem buyItem : createOrderRequest.getBuyItemList())
        {
            Product product = productDao.getProductById(buyItem.getProductId() );

            //檢查product是否存在 庫存是否足夠
            if (product == null) {
                log.warn("商品 {} 不存在", buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }else if(product.getStock() < buyItem.getQuantity()){
                log.warn("商品 {} 庫存不足，無法購買，剩餘庫存 {}，欲購買數量 {}",
                        buyItem.getProductId(),product.getStock(),buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            //扣除商品庫存
            productDao.updateStock(product.getProductId(),product.getStock() - buyItem.getQuantity());






            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount = totalAmount + amount;

            //轉換BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }


        //創建訂單
        Integer orderId = orderDao.createOrder(userId,totalAmount);
        orderDao.createOrderItems(orderId,orderItemList);
        return orderId;
    }
}
