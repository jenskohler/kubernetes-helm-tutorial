package de.kohlerjens.springboot.jpa.controller;

import de.kohlerjens.springboot.jpa.model.Order;
import de.kohlerjens.springboot.jpa.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/addOrder")
    public Order addOrder(@RequestBody Order order) {
        return service.saveOrder(order);
    }

    @PostMapping("/addOrders")
    public List<Order> addOrders(@RequestBody List<Order> orders) {
        return service.saveOrders(orders);
    }

    @GetMapping("/orders")
    public List<Order> findAllOrders() {
        return service.getOrders();
    }

    @GetMapping("/orderById/{id}")
    public Order findOrderById(@PathVariable String id) {
        return service.getOrderById(id);
    }

    @GetMapping("/order/{name}")
    public List<Order> findOrderByDate(@PathVariable Date orderDate) {
        return service.getOrderByDate(orderDate);
    }

    @PutMapping("/update")
    public Order updateOrder(@RequestBody Order Order) {
        return service.updateOrder(Order);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOrder(@PathVariable String id) {
        return service.deleteOrder(id);
    }

    //TODO: add feignclient that receives customer data, i.e. @GetMapping(/order/{id}/{customerId})
}
