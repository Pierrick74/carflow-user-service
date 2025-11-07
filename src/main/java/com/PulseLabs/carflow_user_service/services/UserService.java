package com.PulseLabs.carflow_user_service.services;
import com.PulseLabs.carflow_user_service.Error.DBErrorException;
import com.PulseLabs.carflow_user_service.Error.InputErrorException;
import com.PulseLabs.carflow_user_service.Error.UserNotFound;
import com.PulseLabs.carflow_user_service.db.UserRepository;
import com.PulseLabs.carflow_user_service.model.User;
import com.PulseLabs.carflow_user_service.model.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final ModelMapper modelMapper = new ModelMapper();

    private final UserRepository dbRepository;

    public UserService(UserRepository dbRepository) {
        this.dbRepository = dbRepository;
    }

    public UserDTO saveClient(UserDTO client){

        checkInput(client);

        User newClient = new User();
        newClient.setName(client.getName());
        newClient.setSurname(client.getSurname());
        newClient.setBirthday(client.getBirthday());
        newClient.setDrivingLicenseNumber(client.getDrivingLicenseNumber());
        newClient.setRegistrationDate(client.getRegistrationDate());

        save(newClient);

        return modelMapper.map(newClient, UserDTO.class);
    }

    private void checkInput(UserDTO client) {
        if(client.getName() == null || client.getName().isEmpty() ||
                client.getSurname() == null || client.getSurname().isEmpty() ||
                client.getDrivingLicenseNumber() == null || client.getDrivingLicenseNumber().isEmpty() ||
                client.getBirthday() == null || client.getRegistrationDate() == null
        ) {
            throw new InputErrorException("Tous les champs sont obligatoires");
        }

        LocalDate eighteenYearsAgo = LocalDate.now().minusYears(18);
        if(client.getBirthday().isAfter(eighteenYearsAgo)) {
            throw new InputErrorException("L'utilisateur doit avoir au moins 18 ans");
        }

        if(client.getRegistrationDate().isAfter(LocalDate.now())) {
            throw new InputErrorException("Problème de date sur le permis");
        }


    }

    public List<UserDTO> getClients(String name, String surname) {
        List<User> clientList;

        // Vérifier si les paramètres sont null ou vides
        boolean nameEmpty = (name == null || name.isEmpty());
        boolean surnameEmpty = (surname == null || surname.isEmpty());

        if (nameEmpty && surnameEmpty) {
            clientList = (List<User>) dbRepository.findAll();
            return convert(clientList);
        }

        if (!nameEmpty && !surnameEmpty) {
            clientList = dbRepository.findByNameAndSurname(name, surname);
        } else if (surnameEmpty) {
            clientList = dbRepository.findByName(name);
        } else {
            clientList = dbRepository.findBySurname(surname);
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
        User result =  dbRepository.findById((long) id).orElse(null);

        if(result != null) {
            return modelMapper.map(result, UserDTO.class);
        } else {
            throw new UserNotFound("le Client n'existe pas");
        }
    }

    public UserDTO updateClient(UserDTO client){
        try {
            if(getClientById(client.getId()) != null){
                checkInput(client);
                save(modelMapper.map(client, User.class));
                return client;
            } else {
                throw new UserNotFound("Client " + client.getId() + " not found");
            }
        } catch (UserNotFound e) {
            throw new UserNotFound(e.getMessage());
        }
    }

    private void save(User user) {
        try {
            dbRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new DBErrorException("Problème de sauvegarde en DB:" + e.getMessage());
        }
    }

    public void deleteClient(int id){
        try {
            if(getClientById(id) != null) {
                dbRepository.deleteById((long) id);
            } else {
                throw new UserNotFound("Client " + id + " not found");
            }

        } catch (UserNotFound e) {
            throw new UserNotFound(e.getMessage());
        }
    }

    public Integer getClientAgeWithId(int id){
        try {
            User user = dbRepository.findById((long) id).orElse(null);
            if(user != null) {
                LocalDate today = LocalDate.now();
                LocalDate birthday = user.getBirthday();
                int age = today.getYear() - birthday.getYear();

                if (today.getMonthValue() < birthday.getMonthValue() ||
                    (today.getMonthValue() == birthday.getMonthValue() && today.getDayOfMonth() < birthday.getDayOfMonth())) {
                    age--;
                }

                return age;
            } else {
                throw new UserNotFound("Client " + id + " not found");
            }

        } catch (UserNotFound e) {
            throw new UserNotFound(e.getMessage());
        }
    }
}
