package com.showroommanagement.repository;

import com.showroommanagement.dto.BikeDetail;
import com.showroommanagement.entity.Showroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowroomRepository extends JpaRepository<Showroom, Integer> {
}
