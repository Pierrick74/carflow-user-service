package com.PulseLabs.carflow_user_service.DAO;

import com.PulseLabs.carflow_user_service.model.User;

import java.util.List;

public interface UserDAO {
    List<User> findAll();
    User findById(int id) throws Exception;
    List<User> findByName(String name) throws Exception;
    void save(User client);
    void delete(int id) throws Exception;
    void update(User client) throws Exception;
    List<User> findByNameAndSurname(String name, String surname);
    List<User> findBySurname(String surname);
}
