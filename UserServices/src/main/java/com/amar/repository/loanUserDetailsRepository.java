package com.amar.repository;

import com.amar.model.Loanuserdetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface loanUserDetailsRepository extends JpaRepository<Loanuserdetails, Integer> {
    @Query(value = "SELECT * FROM loanuserdetails WHERE status != 'verified' OR status IS NULL", nativeQuery = true)
    List<Loanuserdetails> getalluserdetails();
}