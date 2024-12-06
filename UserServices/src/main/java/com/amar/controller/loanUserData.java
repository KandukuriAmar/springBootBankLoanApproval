package com.amar.controller;

import com.amar.model.Loanuserdetails;
import com.amar.service.loanUserDetailsService;
import com.amar.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/user")
@CrossOrigin("*")
public class loanUserData {

    @Autowired
    loanUserDetailsService las;

    @Autowired
    private userService userService;

    @GetMapping("/getallloanuserdetails")
    @ResponseBody
    public List<Loanuserdetails> getAllLoanuserdetails() {
        List<Loanuserdetails> details = las.getAllLoanuserdetails();

        return details;
    }

    @PostMapping("/approveLoan/{id}")
    public ResponseEntity<?> approveLoan(@PathVariable int id){
        String res = las.approveloan(id);
        if("ok".equals(res)){
            return ResponseEntity.ok("Loan detailed approved");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loan with ID " + id + " not found.");
    }

    @PostMapping("/rejectLoan/{id}")
    public ResponseEntity<?> rejectLoan(@PathVariable int id){
        String res = las.rejectloan(id);
        if("ok".equals(res)){
            return ResponseEntity.ok("Loan detailed rejected");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loan with ID " + id + " not found.");
    }

    @PostMapping("/loandetails")
    public ResponseEntity<String> addDetails(
            @RequestParam("fullname") String fullname,
            @RequestParam("loanType") String loanType,
            @RequestParam("email") String email,
            @RequestParam("phoneNumber") long phoneNumber, // Changed to match frontend
            @RequestParam("document") MultipartFile file) { // Changed to match frontend

        Loanuserdetails loanDetails = new Loanuserdetails();
        loanDetails.setFullname(fullname);
        loanDetails.setLoantype(loanType);
        loanDetails.setEmail(email);
        loanDetails.setPhonenumber(phoneNumber);

        String result = userService.addLoandetails(loanDetails, file);
        if ("added".equals(result)) {
            return ResponseEntity.ok("Loan details added successfully.");
        } else if ("not added".equals(result)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add loan details.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while adding details.");
        }
    }

}
