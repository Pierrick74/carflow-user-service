package com.PulseLabs.carflow_user_service.DAO;

import com.PulseLabs.carflow_user_service.Error.DBErrorException;
import com.PulseLabs.carflow_user_service.db.UserRepository;
import com.PulseLabs.carflow_user_service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserDAOImpl implements UserDAO {
    @Autowired
    private UserRepository dbRepository;

    @Override
    public void save(User user) {
        try {
            dbRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new DBErrorException("Problème de sauvegarde en DB:" + e.getMessage());
        }
    }
}
