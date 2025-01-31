package com.showroommanagement.repository;

import com.showroommanagement.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    //    Users findByEmailId(String emailId);
    Optional<Users> findByEmailId(String emailId);

    boolean existsByEmailId(String emailId);
}
