package com.amar.controller;

import com.amar.model.Loanuserdetails;
import com.amar.model.User;
import com.amar.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@CrossOrigin("*")
public class userAuth {

    @Autowired
    private userService userService;

    @GetMapping("/")
    @ResponseBody
    public String landing(){
        return "index";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home(){
        return "Home";
    }

    @PostMapping("/validlogin")
    @ResponseBody
    public String validLogin(@RequestBody User user) {
        return userService.validLogin(user);
    }


    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        Optional<User> existingUser = userService.addUser(user);

        if (existingUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("An account with this email already exists.");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(existingUser.get());
        }
    }


    @DeleteMapping("/deleteUser")
    @ResponseBody
    public ResponseEntity<String> deleteUser(@RequestBody String email) {
        String result = userService.deleteUser(email);
        if (result.equals("User deleted")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }


    @PutMapping("/updateUser")
    @ResponseBody
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @GetMapping("/getUser")
    @ResponseBody
    public ResponseEntity<User> getUser(@RequestParam String email) {
        User gotUser = userService.getUser(email);
        if (gotUser != null) {
            System.out.println(gotUser);
            return ResponseEntity.ok().body(gotUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
