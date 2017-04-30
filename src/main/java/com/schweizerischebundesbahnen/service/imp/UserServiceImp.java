package com.schweizerischebundesbahnen.service.imp;

import com.schweizerischebundesbahnen.model.User;
import com.schweizerischebundesbahnen.repository.UserRepository;
import com.schweizerischebundesbahnen.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aleksandrprendota on 25.03.17.
 *
 * Implimentation UserService interface
 */

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Creating a new user in database
     * @param user new User entity
     */
    @Override
    public void addUser(User user){
        userRepository.save(user);
    }

    /**
     * delete User form database
     * @param user existing user entity
     */
    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    /**
     * Getting user form database by his ID
     * @param id - User's entity id
     * @return existing User entity
     */
    @Override
    public User getUserById(Long id){
        return userRepository.findOne(id);
    }

    /**
     * Getting user entity by email
     * @param email User's email
     * @return user with adjusted email
     */
    @Override
    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    /**
     * Creating new user in database
     * @param user
     * @return new User entity which has been saved
     */
    @Override
    public User save(User user){
        return userRepository.save(user);
    }

    /**
     * get list of all users
     * @return
     */
    @Override
    public List<User> findAllUser() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

}
