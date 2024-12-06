package com.amar.service;

import com.amar.model.Loanuserdetails;
import com.amar.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface userService {
    Optional<User> addUser(User user);
    String deleteUser(String email);
    User updateUser(User user);
    User getUser(String email);
    String validLogin(User user);
    String addLoandetails(Loanuserdetails userdetails, MultipartFile file);
}