// Service Implementation package: com.amar.service

package com.amar.service;

import com.amar.model.Loanuserdetails;
import com.amar.model.User;
import com.amar.repository.loanUserDetailsRepository;
import com.amar.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.util.Optional;

@Service
public class userServiceImpl implements userService {

    @Autowired
    private userRepository repo;

    @Autowired
    private loanUserDetailsRepository loanrepo;

    @Override
    public Optional<User> addUser(User user){
        User existingUser = repo.findByEmail(user.getEmail());

        if (existingUser != null) {
            return Optional.empty();
        } else {
            String givenPass = user.getPassword();
            BCryptPasswordEncoder passEncode = new BCryptPasswordEncoder();
            String encryptPass = passEncode.encode(givenPass);
            user.setPassword(encryptPass);

            return Optional.of(repo.save(user));
        }
    }



    @Transactional
    @Override
    public String deleteUser(String email) {
        User existUser = repo.findByEmail(email);
        if (existUser != null) {
            repo.delete(existUser);
            return "User deleted";
        } else {
            return "User with this email not present";
        }
    }


    @Override
    public User updateUser(User user) {
        User existingUser = repo.findByEmail(user.getEmail());
        if (existingUser != null) {
            existingUser.setEmail(user.getEmail());
            existingUser.setFullname(user.getFullname());
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            return repo.save(existingUser);
        }
        return null;
    }

    @Override
    public User getUser(String email) {
        User existingUser = repo.findByEmail(email);
        return existingUser;
    }


    @Override
    public String validLogin(User user) {
        User foundUser = repo.findByEmail(user.getEmail());

        if (foundUser == null) {
            return "User with this email not found";
        }

        else if (user.getPassword().equals(foundUser.getPassword())) {
            return "valid";
        } else {
            return "Invalid password";
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