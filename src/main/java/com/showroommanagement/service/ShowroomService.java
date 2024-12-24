package com.showroommanagement.service;

import com.showroommanagement.entity.Showroom;
import com.showroommanagement.repository.ShowroomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ShowroomService {
    private final ShowroomRepository showroomRepository;

    public ShowroomService(final ShowroomRepository showroomRepository) {
        this.showroomRepository = showroomRepository;
    }

    public Showroom createShowroom(final Showroom showroom) {
        return this.showroomRepository.save(showroom);
    }

    public Showroom retrieveById(final Integer id) {
        return this.showroomRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No Showroom found for this id"));
    }

    public List<Showroom> retrieveAll() {
        return this.showroomRepository.findAll();
    }


//    public List <BikeDetail> retrieveBySearch(final String search){
//        return this.showroomRepository.retrieveBySearch(search);
//
//    }

    public Showroom updateById(final Showroom showroom, final Integer id) {
        final Optional<Showroom> showroomOptional = this.showroomRepository.findById(id);
        if (showroomOptional.isEmpty()) {
            throw new IllegalArgumentException("Showroom Not Found");
        }
        final Showroom showroomObject = showroomOptional.get();
        if (showroom.getName() != null) {
            showroomObject.setName(showroom.getName());
        }
        if (showroom.getBrand() != null) {
            showroomObject.setBrand(showroom.getBrand());
        }
        if (showroom.getAddress() != null) {
            showroomObject.setAddress(showroom.getAddress());
        }
        if (showroom.getContactNumber() != 0) {
            showroomObject.setContactNumber(showroom.getContactNumber());
        }
        return this.showroomRepository.save(showroomObject);
    }

    public String deleteById(final Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Showroom ID cannot be null.");
        }
        final Showroom showroom = this.showroomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Showroom Id Not found"));
        this.showroomRepository.deleteById(id);
        return "Deleted Successfully";
    }

}
