package com.bank.resources;

import com.bank.dto.UserDTO;
import com.bank.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserResources {

    private UserService userService;

    @Autowired
    public UserResources(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/currentUser")
    public UserDTO getCurrentUser() {
        return this.userService.getCurrentUser();
    }

    @PostMapping("/disconnect")
    public void logout() {
        SecurityContextHolder.clearContext();
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<?> forgotPassword(@RequestBody UserDTO userDto) {
        return this.userService.forgotPassword(userDto);
    }

    @PostMapping("/updateUserInfo")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO) {
        return this.userService.updateUserInfo(userDTO);
    }
}
