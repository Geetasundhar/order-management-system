package com.example.order_management.service;



import com.example.order_management.entity.Order;
import com.example.order_management.repository.OrderRepository;
import com.example.order_management.request.OrderRequest;
import com.example.order_management.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public Response save(OrderRequest request) {

        Response response = Response.getInstance();

        Order order = new Order();

        order.setCustomerName(request.getCustomerName());
        order.setProductName(request.getProductName());
        order.setQuantity(request.getQuantity());
        order.setPrice(request.getPrice());
        order.setStatus(request.getStatus());

        orderRepository.save(order);

        response.setRemarks("Order Saved Successfully");
        response.setData(order);

        return response;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
    public Order getOrderById(Long id) {

        return orderRepository.findById(id).orElse(null);
    }
    public void updateOrder(Long id, OrderRequest request) {

        Order order = orderRepository.findById(id).orElse(null);

        if(order != null) {

            order.setCustomerName(request.getCustomerName());
            order.setProductName(request.getProductName());
            order.setQuantity(request.getQuantity());
            order.setPrice(request.getPrice());
            order.setStatus(request.getStatus());

            orderRepository.save(order);
        }
    }
    public List<Order> searchOrders(String keyword) {

        List<Order> customerOrders =
                orderRepository.findByCustomerNameContainingIgnoreCase(keyword);

        if(!customerOrders.isEmpty()) {
            return customerOrders;
        }

        List<Order> productOrders =
                orderRepository.findByProductNameContainingIgnoreCase(keyword);

        if(!productOrders.isEmpty()) {
            return productOrders;
        }

        return orderRepository.findByStatusContainingIgnoreCase(keyword);
    }
    public long getTotalOrders() {
        return orderRepository.count();
    }

    public long getPendingOrders() {
        return orderRepository.countByStatus("Pending");
    }

    public long getDeliveredOrders() {
        return orderRepository.countByStatus("Delivered");
    }

    public double getTotalRevenue() {

        List<Order> orders = orderRepository.findAll();

        double total = 0;

        for(Order order : orders) {
            total += order.getPrice();
        }

        return total;
    }
}