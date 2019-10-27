package com.example.demo.dao;

import com.example.demo.dto.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDataRepository extends CrudRepository<User, Integer> {
    User findByUserLoginAndUserPassword(String userLogin, String userPassword);
}