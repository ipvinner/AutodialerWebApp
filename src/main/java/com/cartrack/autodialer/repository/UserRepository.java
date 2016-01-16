package com.cartrack.autodialer.repository;

import com.cartrack.autodialer.domain.User;

import java.util.List;

/**
 * Created by vinner on 30.12.2015.
 */
public interface UserRepository {
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    User getByLogin(String login);

    List<User> getAll();
}
