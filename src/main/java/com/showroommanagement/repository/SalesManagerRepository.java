package com.showroommanagement.repository;

import com.showroommanagement.entity.SalesManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesManagerRepository extends JpaRepository<SalesManager, Integer> {
}
