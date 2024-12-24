package com.showroommanagement.service;

import com.showroommanagement.dto.BikeDetail;
import com.showroommanagement.entity.Sales;
import com.showroommanagement.repository.SalesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<BikeDetail> retrieveSalesByShowroomAndBikeName(final String showroomName, final String bikeName) {
        List<Sales> sales = this.salesRepository.retrieveSalesByShowroomAndBikeName(showroomName, bikeName);
        List<BikeDetail> bikeDetailList = new ArrayList<>();
        for (Sales sales1 : sales) {
            BikeDetail bikeDetail = new BikeDetail();
            bikeDetail.setShowroomName(sales1.getBike().getSalesman().getShowroom().getName());
            bikeDetail.setShowroomBrand(sales1.getBike().getSalesman().getShowroom().getBrand());
            bikeDetail.setSalesManagerName(sales1.getBike().getSalesman().getShowroom().getSalesmanager().getName());
            // bikeDetail.setSalesManagerName(sales1.getBike().getSalesman().getShowroom().get);
            bikeDetail.setSalesmanName(sales1.getCustomer().getSalesman().getName());
            bikeDetail.setBikeName(sales1.getBike().getName());
            bikeDetail.setBikePrice(sales1.getBike().getPrice());
            bikeDetail.setCustomerName(sales1.getCustomer().getName());
            bikeDetail.setCustomerEmail(sales1.getCustomer().getEmail());
            bikeDetail.setCustomerContactNumber(sales1.getCustomer().getContactNumber());
            bikeDetail.setSalesDate(sales1.getSalesDate());
            bikeDetailList.add(bikeDetail);
        }
        return bikeDetailList;
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
        if (sales.getCustomer() != null) {
            salesObject.setCustomer(sales.getCustomer());
        }
        if (sales.getBike() != null) {
            salesObject.setBike(sales.getBike());
        }
        return this.salesRepository.save(salesObject);
    }

    public String deleteById(final Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Sales not Found");
        }
        final Sales sales = this.salesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Sales not Found"));
        this.salesRepository.deleteById(id);
        return "Deleted Successfully.";
    }
}
