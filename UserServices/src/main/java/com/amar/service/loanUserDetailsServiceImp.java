package com.amar.service;

import com.amar.model.Loanuserdetails;
import com.amar.repository.loanUserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.util.Optional;

import java.util.List;

@Service
public class loanUserDetailsServiceImp implements loanUserDetailsService{

    @Autowired
    loanUserDetailsRepository userdetailsrepo;

    @Autowired
    private loanUserDetailsRepository loanrepo;

    @Override
    public List<Loanuserdetails> getAllLoanuserdetails() {
        List<Loanuserdetails> details = userdetailsrepo.getalluserdetails();

        // Ensure that loanCertificate is converted to Base64
        for (Loanuserdetails detail : details) {
            try {
                if (detail.getLoanCertificate() != null) {
                    detail.setLoanCertificateBase64(detail.getLoanCertificateBase64());
                }
            } catch ( Exception e) {
                e.printStackTrace();
            }
        }
        return details;
    }

    @Override
    public String approveloan(int id) {
        Optional<Loanuserdetails> userdetailsOptional = userdetailsrepo.findById(id);

        if (userdetailsOptional.isPresent()) {
            Loanuserdetails userdetails = userdetailsOptional.get();
            userdetails.setStatus("verified"); // Approve status
            userdetailsrepo.save(userdetails);
            return "ok";
        } else {
            return "not ok";
        }
    }

    @Override
    public String rejectloan(int id) {
        Optional<Loanuserdetails> userdetailsOptional = userdetailsrepo.findById(id);

        if (userdetailsOptional.isPresent()) {
            Loanuserdetails userdetails = userdetailsOptional.get();
            userdetails.setStatus("rejected"); // Reject status
            userdetailsrepo.save(userdetails);
            return "ok";
        } else {
            return "not ok";
        }
    }

    @Override
    public String addLoandetails(Loanuserdetails userdetails, MultipartFile file) {
        try {
            // Convert MultipartFile to Blob and store
            Blob blob = new javax.sql.rowset.serial.SerialBlob(file.getBytes());
            userdetails.setLoanCertificate(blob); // Ensure your entity supports Blob

            if (loanrepo.save(userdetails) != null) {
                return "added";
            } else {
                return "not added";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error while adding details";
        }
    }

}