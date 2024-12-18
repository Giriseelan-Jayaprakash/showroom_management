package com.showroommanagement.controller;

import com.showroommanagement.entity.Salesman;
import com.showroommanagement.service.SalesmanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/salesman")
public class SalesmanController {
    private final SalesmanService salesmanService;

    public SalesmanController(final SalesmanService salesmanService) {
        this.salesmanService = salesmanService;
    }

    @PostMapping("/create")
    public Salesman createSalesman(@RequestBody final Salesman salesman) {
        return this.salesmanService.createSalesman(salesman);
    }

    @GetMapping("/retrieve-id/{id}")
    public Salesman retrieveById(@PathVariable("id") Integer id) {
        return this.salesmanService.retrieveById(id);
    }

    @GetMapping("/retrieve-all")
    public List<Salesman> retrieveAll() {
        return this.salesmanService.retrieveAll();
    }

    @PutMapping("/update-id/{id}")
    public Salesman updateById(@PathVariable("id") final Integer id, final Salesman salesman) {
        return this.salesmanService.updateById(id, salesman);
    }

    @DeleteMapping("/delete-id/{id}")
    public void deleteById(@PathVariable("id") final Integer id) {
        this.salesmanService.deleteById(id);
    }
}
