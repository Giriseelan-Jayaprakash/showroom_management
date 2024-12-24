package com.showroommanagement.service;

import com.showroommanagement.entity.Salesmanager;
import com.showroommanagement.repository.SalesmanagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesmanagerService {
    private final SalesmanagerRepository salesmanagerRepository;

    public SalesmanagerService(final SalesmanagerRepository salesmanagerRepository) {
        this.salesmanagerRepository = salesmanagerRepository;
    }

    public Salesmanager createSalesManager(final Salesmanager salesManager) {
        return this.salesmanagerRepository.save(salesManager);
    }

    public Salesmanager retrieveById(final Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Sales Manager cannot be null");
        }
        final Optional<Salesmanager> salesmanager = this.salesmanagerRepository.findById(id);
        if (salesmanager.isPresent()) {
            return salesmanager.get();
        } else {
            throw new IllegalArgumentException("SalesManager not Found");
        }
    }

    public List<Salesmanager> retrieveALl() {
        return this.salesmanagerRepository.findAll();
    }

    public Salesmanager updateById(final Integer id, final Salesmanager salesmanager) {
        final Optional<Salesmanager> salesmanagerOptional = this.salesmanagerRepository.findById(id);
        if (salesmanagerOptional.isEmpty()) {
            throw new IllegalArgumentException("SalesManager not Found");
        }
        final Salesmanager salesmanagerObject = salesmanagerOptional.get();
        if (salesmanager.getName() != null) {
            salesmanagerObject.setName(salesmanager.getName());
        }
        if (salesmanager.getAddress() != null) {
            salesmanagerObject.setAddress(salesmanager.getAddress());
        }
        if (salesmanager.getContactNumber() != 0) {
            salesmanagerObject.setContactNumber(salesmanager.getContactNumber());
        }
//        if (salesmanager.getShowroom() != null) {
//            salesmanagerObject.setShowroom(salesmanager.getShowroom());
//        }
        return this.salesmanagerRepository.save(salesmanagerObject);
    }

    public String deleteById(final Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Sales Manager cannot be null");
        }
        final Salesmanager salesmanager = this.salesmanagerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("SalesManager not Found"));
        salesmanagerRepository.deleteById(id);
        return "Deleted Successfully.";
    }
}
