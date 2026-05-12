package com.example.order_management.controller;

import com.example.order_management.entity.Order;
import com.example.order_management.request.OrderRequest;
import com.example.order_management.service.OrderService;
import com.example.order_management.utils.Response;
import com.example.order_management.utils.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

  
    @PostMapping("/save")
    public String createOrder(OrderRequest request) {

        logger.info("Order Request Received : {}", request);

        orderService.save(request);

        return "redirect:/orders-page";
    }

   
    @ResponseBody
    @GetMapping("/fetch")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

 
    @GetMapping("/")
    public String home() {
        return "index";
    }

  
    @GetMapping("/add-order")
    public String addOrderPage() {
        return "add-order";
    }

    
    @GetMapping("/orders-page")
    public String ordersPage(Model model) {

        model.addAttribute("orders",
                orderService.getAllOrders());

        return "orders";
    }
    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {

        orderService.deleteOrder(id);

        return "redirect:/orders/orders-page";
    }
    @GetMapping("/edit/{id}")
    public String editOrder(@PathVariable Long id, Model model) {

        Order order = orderService.getOrderById(id);

        model.addAttribute("order", order);

        return "edit-order";
    }
    @PostMapping("/update/{id}")
    public String updateOrder(
            @PathVariable Long id,
            OrderRequest request) {

        orderService.updateOrder(id, request);

        return "redirect:/orders/orders-page";
    }
    @GetMapping("/search")
    public String searchOrders(
            @RequestParam String keyword,
            Model model) {

        model.addAttribute("orders",
                orderService.searchOrders(keyword));

        return "orders";
    }
}