package com.showroommanagement.controller;

import com.showroommanagement.entity.Sales;
import com.showroommanagement.service.SalesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sales")
public class SalesController {
    private final SalesService salesService;

    public SalesController(final SalesService salesService) {
        this.salesService = salesService;
    }

    @PostMapping("/create")
    public Sales createSales(@RequestBody final Sales sales) {
        return this.salesService.createSales(sales);
    }

    @GetMapping("/retrieve-id/{id}")
    public Sales retrieveById(@PathVariable("id") final Integer id) {
        return this.salesService.retrieveById(id);
    }

    @GetMapping("retrieve-all")
    public List<Sales> retrieveAll() {
        return this.salesService.retrieveAll();
    }

    @PutMapping("/update-id/{id}")
    public Sales updateById(@PathVariable("id") Integer id, @RequestBody final Sales sales) {
        return this.salesService.updateById(id, sales);
    }

    @DeleteMapping("/delete-id/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        this.salesService.deleteById(id);
    }
}
