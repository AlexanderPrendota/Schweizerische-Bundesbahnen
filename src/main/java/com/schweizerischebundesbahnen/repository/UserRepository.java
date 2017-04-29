package com.schweizerischebundesbahnen.repository;

import com.schweizerischebundesbahnen.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by aleksandrprendota on 25.03.17.
 */
@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    User findByEmail(String email);

}
