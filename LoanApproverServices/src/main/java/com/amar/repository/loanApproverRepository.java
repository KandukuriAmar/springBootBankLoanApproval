package com.amar.repository;

import com.amar.model.Loanapprover;
//import com.amar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface loanApproverRepository extends JpaRepository<Loanapprover, Long> {
    Loanapprover findByEmail(String email);
}
