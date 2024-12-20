package com.showroommanagement.controller;

import com.showroommanagement.dto.ResponseDTO;
import com.showroommanagement.entity.Showroom;
import com.showroommanagement.service.ShowroomService;
import com.showroommanagement.util.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/showroom")
public class ShowroomController {
    private final ShowroomService showroomService;

    public ShowroomController(final ShowroomService showroomService) {
        this.showroomService = showroomService;
    }

    @PostMapping("/create")
    public ResponseDTO createShowroom(@RequestBody final Showroom showroom) {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.CREATE);
        responseDTO.setStatusCode(HttpStatus.CREATED.value());
        responseDTO.setData(this.showroomService.createShowroom(showroom));
        return responseDTO;
//        return this.showroomService.createShowroom(showroom);
    }

    @GetMapping("/retrieve-id/{id}")
    public ResponseDTO retrieveById(@PathVariable final Integer id) {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.RETRIEVE);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(this.showroomService.retrieveById(id));
        return responseDTO;
    }

    @GetMapping("/retrieve-all")
    public ResponseDTO retrieveAll() {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.RETRIEVE);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(this.showroomService.retrieveAll());
        return responseDTO;
    }

    @PutMapping("/update-id/{id}")
    public ResponseDTO updateById(@PathVariable("id") final Integer id, @RequestBody final Showroom showroom) {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.UPDATE);
        responseDTO.setStatusCode(HttpStatus.CREATED.value());
        responseDTO.setData(this.showroomService.updateById(showroom, id));
        return responseDTO;
    }

    @DeleteMapping("/delete-id/{id}")
    public ResponseDTO deleteById(@PathVariable("id") final Integer id) {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.DELETE);
        this.showroomService.deleteById(id);
        responseDTO.setStatusCode(HttpStatus.NO_CONTENT.value());
        return responseDTO;
    }
//    public void deleteById(@PathVariable("id") final Integer id) {
//        this.showroomService.deleteById(id);
//    }
}
