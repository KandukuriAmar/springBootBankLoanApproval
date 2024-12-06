package com.amar.service;

import com.amar.model.Loanuserdetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface loanUserDetailsService {

    public List<Loanuserdetails> getAllLoanuserdetails();
    String addLoandetails(Loanuserdetails userdetails, MultipartFile file);
    public String approveloan(int id);
    public String rejectloan(int id);

}