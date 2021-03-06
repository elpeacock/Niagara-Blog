package com.codeup.repositories;

import com.codeup.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by user on 2/10/17.
 */
@Repository
public interface Users extends CrudRepository<User, Long> {

    User findByUsername(String username);

    User findById(Long id);
}
