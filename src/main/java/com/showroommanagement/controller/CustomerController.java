package com.showroommanagement.controller;

import com.showroommanagement.dto.CustomerDetail;
import com.showroommanagement.entity.Customer;
import com.showroommanagement.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public Customer createCustomer(@RequestBody final Customer customer) {
        return this.customerService.createCustomer(customer);
    }

    @GetMapping("/retrieve-id/{id}")
    public Customer retrieveById(@PathVariable("id") Integer id) {
        return this.customerService.retrieveById(id);
    }

    @GetMapping("/retrieve-all")
    public List<Customer> retrieveAll() {
        return this.customerService.retrieveAll();
    }
    @GetMapping("/retrieve-all-customer")
    public List<CustomerDetail> retrieveAllCustomerDetail() {
        return this.customerService.retrieveAllCustomerDetail();
    }

    @PutMapping("/update-id/{id}")
    public Customer updateById(@PathVariable("id") final Integer id,@RequestBody final Customer customer) {
        return this.customerService.updateById(id, customer);
    }

    @DeleteMapping("/delete-id/{id}")
    public void deleteById(@PathVariable("id") final Integer id) {
        this.customerService.deleteById(id);
    }
}
