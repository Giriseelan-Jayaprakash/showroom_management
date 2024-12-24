package com.showroommanagement.controller;

import com.showroommanagement.dto.ResponseDTO;
import com.showroommanagement.entity.Salesmanager;
import com.showroommanagement.service.SalesmanagerService;
import com.showroommanagement.util.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/salesmanager")
public class SalesmanagerController {
    private final SalesmanagerService salesmanagerService;

    public SalesmanagerController(final SalesmanagerService salesmanagerService) {
        this.salesmanagerService = salesmanagerService;
    }

    @PostMapping("/create")
    public ResponseDTO createSalesManager(@RequestBody final Salesmanager salesmanager) {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.CREATE);
        responseDTO.setStatusCode(HttpStatus.CREATED.value());
        responseDTO.setData(this.salesmanagerService.createSalesManager(salesmanager));
        return responseDTO;
    }

    @GetMapping("/retrieve-id/{id}")
    public ResponseDTO retrieveById(@PathVariable("id") Integer id) {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.RETRIEVE);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(this.salesmanagerService.retrieveById(id));
        return responseDTO;
    }

    @GetMapping("/retrieve-all")
    public ResponseDTO retrieveALl() {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.CREATE);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(this.salesmanagerService.retrieveALl());
        return responseDTO;
    }

    @PutMapping("/update-id/{id}")
    public ResponseDTO updateById(@PathVariable("id") Integer id, @RequestBody Salesmanager salesmanager) {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.UPDATE);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(this.salesmanagerService.updateById(id, salesmanager));
        return responseDTO;
    }

    @DeleteMapping("/delete-id/{id}")
    public ResponseDTO deleteById(@PathVariable("id") Integer id) {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.DELETE);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(this.salesmanagerService.deleteById(id));
        return responseDTO;
    }
}
