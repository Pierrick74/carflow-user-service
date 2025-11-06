package com.PulseLabs.carflow_user_service.services;

import com.PulseLabs.carflow_user_service.DAO.UserDAOImpl;
import com.PulseLabs.carflow_user_service.model.User;
import com.PulseLabs.carflow_user_service.model.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
}
