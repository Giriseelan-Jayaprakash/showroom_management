package com.showroommanagement.controller;

import com.showroommanagement.entity.Salesmanager;
import com.showroommanagement.service.SalesmanagerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/salesmanager")
public class SalesmanagerController {
    private final SalesmanagerService salesmanagerService;

    public SalesmanagerController(final SalesmanagerService salesmanagerService) {
        this.salesmanagerService = salesmanagerService;
    }

    @PostMapping("/create")
    public Salesmanager createSalesmanager(@RequestBody final Salesmanager salesmanager) {
        return this.salesmanagerService.createSalesmanager(salesmanager);
    }

    @GetMapping("/retrieve-id/{id}")
    public Salesmanager retrieveById(@PathVariable("id") Integer id) {
        return this.salesmanagerService.retrieveById(id);
    }

    @GetMapping("/retrieve-all")
    public List<Salesmanager> retrieveALl() {
        return this.salesmanagerService.retrieveALl();
    }

    @PutMapping("/update-id/{id}")
    public Salesmanager updateById(@PathVariable("id") Integer id, @RequestBody Salesmanager salesmanager) {
        return this.salesmanagerService.updateById(id, salesmanager);
    }

    @DeleteMapping("/delete-id/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        this.salesmanagerService.deleteById(id);
    }
}
