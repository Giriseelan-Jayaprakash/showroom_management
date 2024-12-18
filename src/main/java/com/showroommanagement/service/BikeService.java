package com.showroommanagement.service;

import com.showroommanagement.entity.Bike;
import com.showroommanagement.repository.BikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BikeService {
    private final BikeRepository bikeRepository;

    public BikeService(final BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    public Bike createBike(final Bike bike) {
        return this.bikeRepository.save(bike);
    }

    public Bike retrieveById(final Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Bike not Found");
        }
        final Optional<Bike> bike = this.bikeRepository.findById(id);
        if (bike.isPresent()) {
            return bike.get();
        } else {
            throw new IllegalArgumentException("Bike not found");
        }
    }

    public List<Bike> retrieveAll() {
        return this.bikeRepository.findAll();
    }

    public Bike updateById(final Integer id, final Bike bike) {
        final Optional<Bike> bikeOptional = this.bikeRepository.findById(id);
        if (bikeOptional.isEmpty()) {
            throw new IllegalArgumentException("Bike not Found");
        }
        final Bike bikeObject = bikeOptional.get();
        if (bike.getName() != null) {
            bikeObject.setName(bike.getName());
        }
        if (bike.getCc() != 0) {
            bikeObject.setCc(bike.getCc());
        }
        if (bike.getMileage() != 0) {
            bikeObject.setMileage(bike.getMileage());
        }
        if (bike.getPrice() != 0) {
            bikeObject.setPrice(bike.getPrice());
        }
        if (bike.getStock() != null) {
            bikeObject.setStock(bike.getStock());
        }
        if (bike.getShowroom() != null) {
            bikeObject.setShowroom(bike.getShowroom());
        }
        if (bike.getSalesman() != null) {
            bikeObject.setSalesman(bike.getSalesman());
        }
        return bikeRepository.save(bikeObject);
    }

    public void deleteById(final Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Bike not Found");
        }
        final Bike bike = this.bikeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Bike not Found"));
        this.bikeRepository.deleteById(id);
    }
}

