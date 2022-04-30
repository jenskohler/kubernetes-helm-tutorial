package de.kohlerjens.springboot.jpa.controller;

import de.kohlerjens.springboot.jpa.model.Customer;
import de.kohlerjens.springboot.jpa.model.Order;
import de.kohlerjens.springboot.jpa.service.CustomerService;
import de.kohlerjens.springboot.jpa.service.OrderService;
import de.kohlerjens.springboot.jpa.tools.RestTemplateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class CustomerController {

    @Value("${feign.url}")
    String url;
    @Value("${server.ssl.trust-store-password:#{null}}")
    String truststorePassword;
    @Value("${server.ssl.trust-store:#{null}}")
    String truststore;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/addCustomer")
    public Customer addCustomer(@RequestBody Customer Customer) {
        return customerService.saveCustomer(Customer);
    }

    @PostMapping("/addCustomers")
    public List<Customer> addCustomers(@RequestBody List<Customer> Customers) {
        return customerService.saveCustomers(Customers);
    }

    @GetMapping("/customers")
    public List<Customer> findAllCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/customerById/{id}")
    public Customer findCustomerById(@PathVariable String id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/customer/{name}")
    public List<Customer> findCustomerByLastName(@PathVariable String name) {
        return customerService.getCustomerByLastName(name);
    }

    @PutMapping("/update")
    public Customer updateCustomer(@RequestBody Customer Customer) {
        return customerService.updateCustomer(Customer);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable String id) {
        return customerService.deleteCustomer(id);
    }

    @GetMapping("/{customerId}/orders")
    public List<Order> findCustomerOrders(@PathVariable String customerId) {
        return orderService.getAllCustomerOrders(customerId);
    }

    @PostMapping("{customerId}/addOrder")
    public Order addOrder(@PathVariable String customerId, @RequestBody Order order) {
        order.setCustomerId(customerId);
        return orderService.addOrder(order);
    }

    @PostMapping(path = "/restTemplate/{customerId}/addOrder", consumes = "application/json", produces = "application/json")
    public String addRestTempateOrder(Order order) {
        String endpoint = "/addOrder";
        RestTemplate restTemplate = new RestTemplateFactory().create(truststore, truststorePassword);
        HttpEntity<Order> request = new HttpEntity<>(order);

        ResponseEntity<Order> response = restTemplate
                .exchange(url+endpoint, HttpMethod.POST, request, Order.class);

        return response.getStatusCode().toString();

    }

}
