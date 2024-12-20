package com.showroommanagement.controller;

import com.showroommanagement.dto.ResponseDTO;
import com.showroommanagement.entity.Sales;
import com.showroommanagement.service.SalesService;
import com.showroommanagement.util.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sales")
public class SalesController {
    private final SalesService salesService;

    public SalesController(final SalesService salesService) {
        this.salesService = salesService;
    }

    @PostMapping("/create")
    public ResponseDTO createSales(@RequestBody final Sales sales) {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.CREATE);
        responseDTO.setStatusCode(HttpStatus.CREATED.value());
        responseDTO.setData(this.salesService.createSales(sales));
        return responseDTO;
    }

    @GetMapping("/retrieve-id/{id}")
    public ResponseDTO retrieveById(@PathVariable("id") final Integer id) {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.RETRIEVE);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(this.salesService.retrieveById(id));
        return responseDTO;
    }

    @GetMapping("retrieve-all")
    public ResponseDTO retrieveAll() {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.RETRIEVE);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(this.salesService.retrieveAll());
        return responseDTO;
    }

    @PutMapping("/update-id/{id}")
    public ResponseDTO updateById(@PathVariable("id") Integer id, @RequestBody final Sales sales) {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.UPDATE);
        responseDTO.setStatusCode(HttpStatus.CREATED.value());
        responseDTO.setData(this.salesService.updateById(id, sales));
        return responseDTO;
    }

    @DeleteMapping("/delete-id/{id}")
    public ResponseDTO deleteById(@PathVariable("id") Integer id) {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.DELETE);
        responseDTO.setStatusCode(HttpStatus.NO_CONTENT.value());
        this.salesService.deleteById(id);
        return responseDTO;
    }
}
