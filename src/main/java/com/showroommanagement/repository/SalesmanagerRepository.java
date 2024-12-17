package com.showroommanagement.repository;

import com.showroommanagement.entity.Salesmanager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesmanagerRepository extends JpaRepository<Salesmanager, Integer> {
}
