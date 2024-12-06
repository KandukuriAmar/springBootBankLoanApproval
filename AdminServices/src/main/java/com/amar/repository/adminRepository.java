package com.amar.repository;

import com.amar.model.admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface adminRepository extends JpaRepository<admin, Long> {
    admin findByEmail(String email);
}
