package com.PulseLabs.carflow_user_service.web.controller;

import com.PulseLabs.carflow_user_service.model.UserDTO;
import com.PulseLabs.carflow_user_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/Users")
    public ResponseEntity<?> getClients(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "surname" , required = false) String surname) {
        return ResponseEntity.ok(userService.getClients(name, surname));

    }

    @PostMapping("/Users")
    public ResponseEntity<?> createClient(@RequestBody UserDTO user) {
        return ResponseEntity.ok(userService.saveClient(user));
    }


    @PutMapping("/Users")
    public ResponseEntity<?> updateClient(@RequestBody UserDTO user) {
        return ResponseEntity.ok(userService.updateClient(user));
    }

    @GetMapping( "/Users/{id}")
    public ResponseEntity<?> afficherUnClient(@PathVariable int id) {
        return ResponseEntity.ok(userService.getClientById(id));
    }
    @DeleteMapping( "/Users/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable int id) {
        userService.deleteClient(id);
        return ResponseEntity.ok("Client supprimé");
    }
}
