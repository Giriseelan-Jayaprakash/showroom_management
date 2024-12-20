package com.showroommanagement.controller;

import com.showroommanagement.dto.ResponseDTO;
import com.showroommanagement.entity.Bike;
import com.showroommanagement.service.BikeService;
import com.showroommanagement.util.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bike")
public class BikeController {
    private final BikeService bikeService;

    public BikeController(final BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @PostMapping("/create")
    public ResponseDTO createBike(@RequestBody final Bike bike) {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.CREATE);
        responseDTO.setStatusCode(HttpStatus.CREATED.value());
        responseDTO.setData(this.bikeService.createBike(bike));
        return responseDTO;
    }

    @GetMapping("/retrieve-id/{id}")
    public ResponseDTO retrieveById(@PathVariable("id") final Integer id) {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.RETRIEVE);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(this.bikeService.retrieveById(id));
        return responseDTO;
    }

    @GetMapping("/retrieve-all")
    public ResponseDTO retrieveAll() {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.RETRIEVE);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(this.bikeService.retrieveAll());
        return responseDTO;
    }

    @PutMapping("/update-id/{id}")
    public ResponseDTO updateById(@PathVariable("id") final Integer id, @RequestBody Bike bike) {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.UPDATE);
        responseDTO.setStatusCode(HttpStatus.CREATED.value());
        responseDTO.setData(this.bikeService.updateById(id, bike));
        return responseDTO;
    }

    @DeleteMapping("/delete-id/{id}")
    public ResponseDTO deleteById(@PathVariable("id") final Integer id) {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.DELETE);
        responseDTO.setStatusCode(HttpStatus.NO_CONTENT.value());
        this.bikeService.deleteById(id);
        return responseDTO;
    }
}
