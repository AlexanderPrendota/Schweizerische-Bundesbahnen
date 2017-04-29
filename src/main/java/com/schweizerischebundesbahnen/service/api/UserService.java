package com.schweizerischebundesbahnen.service.api;

import com.schweizerischebundesbahnen.model.User;

import java.util.List;

/**
 * Created by aleksandrprendota on 25.03.17.
 */
public interface UserService {

    void addUser(User user);

    void delete(User user);

    User getUserById(Long id);

    User findUserByEmail(String email);

    User save(User user);

    List<User> findAllUser();

}
