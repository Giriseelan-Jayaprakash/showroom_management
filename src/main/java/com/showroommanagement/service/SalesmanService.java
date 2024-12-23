package com.showroommanagement.service;

import com.showroommanagement.entity.Salesman;
import com.showroommanagement.repository.SalesmanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesmanService {
    private final SalesmanRepository salesmanRepository;

    public SalesmanService(final SalesmanRepository salesmanRepository) {
        this.salesmanRepository = salesmanRepository;
    }

    public Salesman createSalesman(final Salesman salesman) {
        return this.salesmanRepository.save(salesman);
    }

    public Salesman retrieveById(final Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Salesman not Found");
        }
        Optional<Salesman> salesman = this.salesmanRepository.findById(id);
        if (salesman.isPresent()) {
            return salesman.get();
        } else {
            throw new IllegalArgumentException("Salesman not Found");
        }
    }

    public List<Salesman> retrieveAll() {
        return this.salesmanRepository.findAll();
    }

    public Salesman updateById(final Integer id, final Salesman salesman) {
        final Optional<Salesman> salesmanOptional = this.salesmanRepository.findById(id);
        {
            if (salesmanOptional.isEmpty()) {
                throw new IllegalArgumentException("Salesman not Found");
            }
            final Salesman salesmanObject = salesmanOptional.get();
            if (salesman.getName() != null) {
                salesmanObject.setName(salesman.getName());
            }
            if (salesman.getAddress() != null) {
                salesmanObject.setAddress(salesman.getAddress());
            }
            if (salesman.getContactNumber() != 0) {
                salesmanObject.setContactNumber(salesman.getContactNumber());
            }
            if (salesman.getExperience() != 0) {
                salesmanObject.setExperience(salesman.getExperience());
            }
            if (salesman.getSalary() != 0) {
                salesmanObject.setSalary(salesman.getSalary());
            }
            if (salesman.getShowroom() != null) {
                salesmanObject.setShowroom(salesman.getShowroom());
            }
            return this.salesmanRepository.save(salesmanObject);
        }
    }

    public String deleteById(final Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Salesman not Found");
        }
        final Salesman salesman = this.salesmanRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Salesman not Found"));
        this.salesmanRepository.deleteById(id);
        return "Deleted Successfully";
    }
}
