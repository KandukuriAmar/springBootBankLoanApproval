package com.amar.service;

import com.amar.model.Loanapprover;
import com.amar.repository.loanApproverRepository;
import org.hibernate.annotations.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class loanApproverServiceImp implements loanApproverService{

    @Autowired
    loanApproverRepository approverrepo;

    @Override
    public Loanapprover addLoanapprover(Loanapprover loanapprover) {
        Loanapprover existingUser = approverrepo.findByEmail(loanapprover.getEmail());

        if (existingUser != null) {
            throw new IllegalArgumentException("Loan approver with this email already exists.");
        } else {
            String givenPass = loanapprover.getPassword();
            BCryptPasswordEncoder passEncode = new BCryptPasswordEncoder();
            String encryptedPass = passEncode.encode(givenPass);
            loanapprover.setPassword(encryptedPass);
            return approverrepo.save(loanapprover);
        }
    }

    @Transactional
    @Override
    public String deleteLoanapprover(String email) {
        Loanapprover existUser = approverrepo.findByEmail(email);
        if (existUser != null) {
            approverrepo.delete(existUser);
            return "Loanapprover deleted";
        } else {
            return "Loanapprover with this email not present";
        }
    }

    @Override
    public Loanapprover updateLoanapprover(Loanapprover loanapprover) {
        Loanapprover existingUser = approverrepo.findByEmail(loanapprover.getEmail());
        if (existingUser != null) {
            existingUser.setEmail(loanapprover.getEmail());
            existingUser.setFullname(loanapprover.getFullname());
            existingUser.setUsername(loanapprover.getUsername());
            existingUser.setPassword(loanapprover.getPassword());
            return approverrepo.save(existingUser);
        }
        return null;
    }

    @Override
    public Loanapprover getLoanapprover(String email) {
        Loanapprover existingUser = approverrepo.findByEmail(email);
        return existingUser;
    }

    @Override
    public String validloginapprover(Loanapprover approver) {
        try {
            // Find approver by email
            Loanapprover existApprover = approverrepo.findByEmail(approver.getEmail());

            if (existApprover != null) {
                if (existApprover.getPassword().equals(approver.getPassword())) {
                    return "valid";
                } else {
                    return "Invalid password";
                }
            } else {
                return "Email not registered";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while validating login approver details";
        }
    }
}
