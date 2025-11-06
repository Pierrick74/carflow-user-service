package com.PulseLabs.carflow_user_service.services;

import com.PulseLabs.carflow_user_service.DAO.UserDAO;
import com.PulseLabs.carflow_user_service.DAO.UserDAOImpl;
import com.PulseLabs.carflow_user_service.Error.UserNotFound;
import com.PulseLabs.carflow_user_service.model.User;
import com.PulseLabs.carflow_user_service.model.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final ModelMapper modelMapper = new ModelMapper();

    private final UserDAOImpl userDAO;

    public UserService(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    public UserDTO saveClient(UserDTO client){

        // Créer un nouveau client SANS spécifier l'ID (la DB le génère automatiquement)
        User newClient = new User();
        newClient.setName(client.getName());
        newClient.setSurname(client.getSurname());
        newClient.setBirthday(client.getBirthday());
        newClient.setDrivingLicenseNumber(client.getDrivingLicenseNumber());
        newClient.setRegistrationDate(client.getRegistrationDate());

        userDAO.save(newClient);

        return modelMapper.map(newClient, UserDTO.class);
    }

    public List<UserDTO> getClients(String name, String surname) {
        List<User> clientList;

        // Vérifier si les paramètres sont null ou vides
        boolean nameEmpty = (name == null || name.isEmpty());
        boolean surnameEmpty = (surname == null || surname.isEmpty());

        if (nameEmpty && surnameEmpty) {
            clientList = userDAO.findAll();
            return convert(clientList);
        }

        if (!nameEmpty && !surnameEmpty) {
            clientList = userDAO.findByNameAndSurname(name, surname);
        } else if (surnameEmpty) {
            clientList = userDAO.findByName(name);
        } else {
            clientList = userDAO.findBySurname(surname);
        }
        return convert(clientList);
    }

    private List<UserDTO> convert(List<User> users) {
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    public UserDTO getClientById(int id){
        User result =  userDAO.findById(id);

        if(result != null) {
            return modelMapper.map(result, UserDTO.class);
        } else {
            throw new UserNotFound("le Client n'existe pas");
        }
    }

    public UserDTO updateClient(UserDTO client){
        try {
            if(getClientById(client.getId()) != null){
                userDAO.update(modelMapper.map(client, User.class));
                return client;
            } else {
                throw new UserNotFound("Client " + client.getId() + " not found");
            }
        } catch (UserNotFound e) {
            throw new UserNotFound(e.getMessage());
        }
    }

    public void deleteClient(int id){
        try {
            if(getClientById(id) != null) {
                userDAO.delete(id);
            } else {
                throw new UserNotFound("Client " + id + " not found");
            }

        } catch (UserNotFound e) {
            throw new UserNotFound(e.getMessage());
        }
    }
}
