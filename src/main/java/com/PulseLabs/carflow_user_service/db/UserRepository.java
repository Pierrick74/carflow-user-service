package com.PulseLabs.carflow_user_service.db;

import com.PulseLabs.carflow_user_service.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByName(String name);
    List<User> findByNameAndSurname(String name, String surname);
    List<User> findBySurname(String surname);
}
