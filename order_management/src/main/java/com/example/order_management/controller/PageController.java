package com.example.order_management.controller;

import com.example.order_management.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @Autowired
    OrderService orderService;

    // =========================
    // HOME PAGE
    // =========================
    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("totalOrders",
                orderService.getTotalOrders());

        model.addAttribute("pendingOrders",
                orderService.getPendingOrders());

        model.addAttribute("deliveredOrders",
                orderService.getDeliveredOrders());

        model.addAttribute("totalRevenue",
                orderService.getTotalRevenue());

        return "index";
    }

    // =========================
    // ADD ORDER PAGE
    // =========================
    @GetMapping("/add-order")
    public String addOrderPage() {
        return "add-order";
    }

    // =========================
    // ORDERS PAGE
    // =========================
    @GetMapping("/orders-page")
    public String ordersPage(Model model) {

        model.addAttribute("orders",
                orderService.getAllOrders());

        return "orders";
    }
}