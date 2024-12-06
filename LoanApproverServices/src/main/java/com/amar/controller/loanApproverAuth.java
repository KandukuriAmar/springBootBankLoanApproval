package com.amar.controller;

import com.amar.model.Loanapprover;
//import com.amar.model.User;
import com.amar.service.loanApproverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/loanapprover")
@CrossOrigin("*")
public class loanApproverAuth {

    @Autowired
    loanApproverService loanapproverService;

    @PostMapping("/addLoanapprover")
    public ResponseEntity<?> addLoanapprover(@RequestBody Loanapprover approveruser) {
        try {
            Loanapprover savedUser = loanapproverService.addLoanapprover(approveruser);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Account created successfully for: " + savedUser.getEmail());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + ex.getMessage());
        }
    }



    @DeleteMapping("/deleteLoanapprover")
    @ResponseBody
    public ResponseEntity<String> deleteLoanapprover(@RequestBody String email) {
        String result = loanapproverService.deleteLoanapprover(email);
        if (result.equals("Loanapprover deleted")) {
            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }


    @PutMapping("/updateLoanapprover")
    @ResponseBody
    public Loanapprover updateLoanapprover(@RequestBody Loanapprover approveruser) {
        return loanapproverService.updateLoanapprover(approveruser);
    }

    @GetMapping("/getLoanapprover")
    @ResponseBody
    public ResponseEntity<Loanapprover> getLoanapprover(@RequestParam String email) {
        Loanapprover gotUser = loanapproverService.getLoanapprover(email);
        if (gotUser != null) {
            System.out.println(gotUser);
            return ResponseEntity.ok().body(gotUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/validloginapprover")
    @ResponseBody
    public String validLoginApprover(@RequestBody Loanapprover loanapprover){
        String res = loanapproverService.validloginapprover(loanapprover);

        if("valid".equals(res)){
            return "valid";
        } else {
            return "Invalid credentials";
        }
    }
}
