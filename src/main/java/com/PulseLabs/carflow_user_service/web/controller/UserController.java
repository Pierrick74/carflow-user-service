package com.PulseLabs.carflow_user_service.web.controller;

import com.PulseLabs.carflow_user_service.model.UserDTO;
import com.PulseLabs.carflow_user_service.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Users", description = "API pour la gestion des utilisateurs/clients")
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Récupérer tous les utilisateurs", description = "Permet de récupérer la liste des utilisateurs avec filtres optionnels par nom et prénom")
    @GetMapping
    public ResponseEntity<?> getClients(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "surname" , required = false) String surname) {
        return ResponseEntity.ok(userService.getClients(name, surname));
    }

    @Operation(summary = "Créer un utilisateur", description = "Permet de créer un nouvel utilisateur")
    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody UserDTO user) {
        return ResponseEntity.ok(userService.saveClient(user));
    }

    @Operation(summary = "Mettre à jour un utilisateur", description = "Permet de mettre à jour les informations d'un utilisateur existant")
    @PutMapping
    public ResponseEntity<?> updateClient(@RequestBody UserDTO user) {
        return ResponseEntity.ok(userService.updateClient(user));
    }

    @Operation(summary = "Récupérer un utilisateur par ID", description = "Permet de récupérer les détails d'un utilisateur spécifique")
    @GetMapping("/{id}")
    public ResponseEntity<?> afficherUnClient(@PathVariable int id) {
        return ResponseEntity.ok(userService.getClientById(id));
    }

    @Operation(summary = "Supprimer un utilisateur", description = "Permet de supprimer un utilisateur par son ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable int id) {
        userService.deleteClient(id);
        return ResponseEntity.ok("Client supprimé");
    }

    @Operation(summary = "Récupérer l'âge d'un utilisateur", description = "Permet de récupérer l'âge d'un utilisateur par son ID")
    @GetMapping("/{id}/age")
    public ResponseEntity<?> getAgeOfClient(@PathVariable int id) {
        return ResponseEntity.ok(userService.getClientAgeWithId(id));
    }

    @Operation(summary = "Vérifier la validité du permis", description = "Permet de vérifier la validité du permis de conduire d'un utilisateur")
    @GetMapping("/{id}/validityLicense")
    public ResponseEntity<?> getValidityLicense(@PathVariable int id) {
        return ResponseEntity.ok(userService.getValidityLicense(id));
    }
}
