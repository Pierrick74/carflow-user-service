package com.PulseLabs.carflow_user_service.DAO;

import com.PulseLabs.carflow_user_service.Error.DBErrorException;
import com.PulseLabs.carflow_user_service.db.UserRepository;
import com.PulseLabs.carflow_user_service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDAOImpl implements UserDAO {
    @Autowired
    private UserRepository dbRepository;

    @Override
    public List<User> findAll() {
        return (List<User>) dbRepository.findAll();
    }

    @Override
    public User findById(int id) {
        return dbRepository.findById((long) id).orElse(null);
    }

    @Override
    public void save(User user) {
        try {
            dbRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new DBErrorException("Problème de sauvegarde en DB:" + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        dbRepository.deleteById((long) id);
    }

    @Override
    public void update(User user) {
        try {
            save(user);
        } catch (DataIntegrityViolationException e) {
            throw new DBErrorException("Problème de sauvegarde en DB:" + e.getMessage());
        }
    }

    @Override
    public List<User> findByName(String name) {
        return dbRepository.findByName(name);
    }

    @Override
    public List<User> findByNameAndSurname(String name, String surname) {
        return dbRepository.findByNameAndSurname(name, surname);
    }

    @Override
    public List<User> findBySurname(String surname) {
        return dbRepository.findBySurname(surname);
    }
}
