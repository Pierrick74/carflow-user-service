package com.PulseLabs.carflow_user_service.web.controller;

import com.PulseLabs.carflow_user_service.model.UserDTO;
import com.PulseLabs.carflow_user_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/Users")
    public ResponseEntity<?> createClient(@RequestBody UserDTO client) {
        return ResponseEntity.ok(userService.saveClient(client));
    }
}
