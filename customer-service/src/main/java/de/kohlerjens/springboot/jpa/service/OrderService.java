package de.kohlerjens.springboot.jpa.service;

import de.kohlerjens.springboot.jpa.model.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "${feign.name}", url = "${feign.url}")
public interface OrderService {

    @PostMapping(value = "/addOrder")
    Order addOrder(@RequestBody Order order);

    @GetMapping(value = "/orders/{customerId}")
    List<Order> getAllCustomerOrders(@PathVariable String customerId);
}
