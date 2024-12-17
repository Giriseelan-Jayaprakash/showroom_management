package com.showroommanagement.controller;

import com.showroommanagement.entity.Showroom;
import com.showroommanagement.service.ShowroomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/showroom")
public class ShowroomController {
    private final ShowroomService showroomService;

    public ShowroomController(final ShowroomService showroomService) {
        this.showroomService = showroomService;
    }

    @PostMapping("/create")
    public Showroom createShowroom(@RequestBody final Showroom showroom) {
        return this.showroomService.createShowroom(showroom);
    }

    @GetMapping("/retrieve-id/{id}")
    public Showroom retrieveById(@PathVariable final Integer id) {
        return this.showroomService.retrieveById(id);
    }

    @GetMapping("/retrieve-all")
    public List<Showroom> retrieveAll() {
        return this.showroomService.retrieveAll();
    }

    @PostMapping("/update-id/{id}")
    public Showroom updateById(@PathVariable("id") final Integer id, @RequestBody final Showroom showroom) {
        return this.showroomService.updateById(showroom, id);
    }

    @DeleteMapping("/delete-id/{id}")
    public void deleteById(@PathVariable("id") final Integer id) {
        this.showroomService.deleteById(id);
    }
}
