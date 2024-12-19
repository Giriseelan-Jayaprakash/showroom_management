package com.showroommanagement.service;

import com.showroommanagement.entity.Sales;
import com.showroommanagement.repository.SalesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesService {
    private final SalesRepository salesRepository;

    public SalesService(final SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public Sales createSales(final Sales sales) {
        return this.salesRepository.save(sales);
    }

    public Sales retrieveById(final Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Sales not Found");
        }
        final Optional<Sales> sales = this.salesRepository.findById(id);
        if (sales.isPresent()) {
            return sales.get();
        } else {
            throw new IllegalArgumentException("Sales not Found");
        }
    }

    public List<Sales> retrieveAll() {
        return this.salesRepository.findAll();
    }

    public Sales updateById(final Integer id, final Sales sales) {
        final Optional<Sales> salesOptional = this.salesRepository.findById(id);
        if (salesOptional.isEmpty()) {
            throw new IllegalArgumentException("Sales not Found");
        }
        final Sales salesObject = salesOptional.get();
        if (sales.getSalesDate() != null) {
            salesObject.setSalesDate(sales.getSalesDate());
        }
        if (sales.getSalesPrice() != 0) {
            salesObject.setSalesPrice(sales.getSalesPrice());
        }
        if (sales.getShowroom() != null) {
            salesObject.setShowroom(sales.getShowroom());
        }
        if (sales.getSalesman() != null) {
            salesObject.setSalesman(sales.getSalesman());
        }
        if (sales.getCustomer() != null) {
            salesObject.setCustomer(sales.getCustomer());
        }
        if (sales.getBike() != null) {
            salesObject.setBike(sales.getBike());
        }
        return this.salesRepository.save(salesObject);
    }

    public void deleteById(final Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Sales not Found");
        }
        final Sales sales = this.salesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Sales not Found"));
        this.salesRepository.deleteById(id);
    }
}
