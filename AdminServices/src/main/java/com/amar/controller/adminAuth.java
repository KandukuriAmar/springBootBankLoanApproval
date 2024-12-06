package com.amar.controller;

import com.amar.model.admin;
import com.amar.service.adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class adminAuth {

    @Autowired
    adminService as;

    @GetMapping("/")
    @ResponseBody
    public String landadmin(){
        return "admin page";
    }

    @PostMapping("/validloginadmin")
    public ResponseEntity<?> validloginadmin(@RequestBody admin administrator){
        String res = as.loginadmin(administrator);

        if("ok".equals(res)){
            return ResponseEntity.ok().body("valid");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error which validating the admin credentials");
        }
    }

}
