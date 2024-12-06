package com.amar.service;

import com.amar.model.admin;
import com.amar.repository.adminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class adminServiceImp implements adminService{

    @Autowired
    adminRepository adminrepo;

    @Override
    public String loginadmin(admin administrator) {
        try {
            admin isValidadmin = adminrepo.findByEmail(administrator.getEmail());

            if(isValidadmin != null) {
                if (isValidadmin.getPassword().equals(administrator.getPassword()) && isValidadmin.getUsername().equals(administrator.getUsername())) {
                    return "ok";
                } else {
                    return "not ok";
                }
            } else {
                return "Invalid admin";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error at verifying the admin credentials";
        }
    }
}
