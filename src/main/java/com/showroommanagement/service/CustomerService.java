package com.showroommanagement.service;

import com.showroommanagement.dto.CustomerDetail;
import com.showroommanagement.entity.Customer;
import com.showroommanagement.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(final Customer customer) {
        return this.customerRepository.save(customer);
    }

    public Customer retrieveById(final Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Customer not Found");
        }
        final Optional<Customer> customer = this.customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new IllegalArgumentException("Customer not Found");
        }
    }

    public List<Customer> retrieveAll() {
        return this.customerRepository.findAll();

    }
    public List<CustomerDetail> retrieveAllCustomerDetail() {
        List<Customer> customer= this.customerRepository.findAll();
        List<CustomerDetail> customerDetails=new ArrayList<>();
        for(Customer customer1:customer){
            CustomerDetail customerDetail=new CustomerDetail();
            customerDetail.setBrand(customer1.getSalesman().getShowroom().getBrand());
            customerDetail.setCompanyName(customer1.getSalesman().getShowroom().getName());
            customerDetail.setEmail(customer1.getEmail());
            customerDetail.setAddress(customer1.getAddress());
            customerDetail.setName(customer1.getName());
            customerDetails.add(customerDetail);
        }
        return customerDetails;
    }

    public Customer updateById(final Integer id, final Customer customer) {
        final Optional<Customer> customerOptional = this.customerRepository.findById(id);
        if (customerOptional.isEmpty()) {
            throw new IllegalArgumentException("Customer not Found");
        }
        final Customer customerObject = customerOptional.get();
        if (customer.getName() != null) {
            customerObject.setName(customer.getName());
        }
        if (customer.getEmail() != null) {
            customerObject.setEmail(customer.getEmail());
        }
        if (customer.getAddress() != null) {
            customerObject.setAddress(customer.getAddress());
        }
        if (customer.getContactNumber() != 0) {
            customerObject.setContactNumber(customer.getContactNumber());
        }
        if (customer.getSalesman() != null) {
            customerObject.setSalesman(customer.getSalesman());
        }
        return customerRepository.save(customerObject);
    }

    public void deleteById(final Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Customer not Found");
        }
        final Customer customer = this.customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Customer not Found"));
        this.customerRepository.deleteById(id);
    }
}
