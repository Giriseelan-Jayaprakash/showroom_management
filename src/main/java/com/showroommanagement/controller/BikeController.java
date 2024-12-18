package com.showroommanagement.controller;

import com.showroommanagement.entity.Bike;
import com.showroommanagement.service.BikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bike")
public class BikeController {
    private final BikeService bikeService;

    public BikeController(final BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @PostMapping("/create")
    public Bike createBike(@RequestBody final Bike bike) {
        return this.bikeService.createBike(bike);
    }

    @GetMapping("/retrieve-id/{id}")
    public Bike retrieveById(@PathVariable("id") final Integer id) {
        return this.bikeService.retrieveById(id);
    }

    @GetMapping("/retrieve-all")
    public List<Bike> retrieveAll() {
        return this.bikeService.retrieveAll();
    }

    @PutMapping("/update-id/{id}")
    public Bike updateById(@PathVariable("id") final Integer id, @RequestBody Bike bike) {
        return this.bikeService.updateById(id, bike);
    }

    @DeleteMapping("/delete-id/{id}")
    public void deleteById(@PathVariable("id") final Integer id) {
        this.bikeService.deleteById(id);
    }
}
