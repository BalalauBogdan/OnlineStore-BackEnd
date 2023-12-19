package dev.bogdanbalalau.onlinestorebackend.service;

import dev.bogdanbalalau.onlinestorebackend.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();
    Optional<User> findById(Integer id);
    Optional<User> findByEmail(String email);
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(User user);
    void deleteById(Integer id);
}
