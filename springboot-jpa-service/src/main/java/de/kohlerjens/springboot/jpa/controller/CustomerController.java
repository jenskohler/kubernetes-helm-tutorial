package de.kohlerjens.springboot.jpa.controller;

import de.kohlerjens.springboot.jpa.model.Customer;
import de.kohlerjens.springboot.jpa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping("/addCustomer")
    public Customer addCustomer(@RequestBody Customer Customer) {
        return service.saveCustomer(Customer);
    }

    @PostMapping("/addCustomers")
    public List<Customer> addCustomers(@RequestBody List<Customer> Customers) {
        return service.saveCustomers(Customers);
    }

    @GetMapping("/customers")
    public List<Customer> findAllCustomers() {
        return service.getCustomers();
    }

    @GetMapping("/customerById/{id}")
    public Customer findCustomerById(@PathVariable String id) {
        return service.getCustomerById(id);
    }

    @GetMapping("/customer/{name}")
    public List<Customer> findCustomerByLastName(@PathVariable String name) {
        return service.getCustomerByLastName(name);
    }

    @PutMapping("/update")
    public Customer updateCustomer(@RequestBody Customer Customer) {
        return service.updateCustomer(Customer);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable String id) {
        return service.deleteCustomer(id);
    }
}
