package com.cartrack.autodialer.service;

import com.cartrack.autodialer.domain.User;
import com.cartrack.autodialer.util.NotFoundException;

import java.util.List;

/**
 * Created by vinner on 28.08.2015.
 */
public interface UserService {
    User save(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByLogin(String login) throws NotFoundException;

    List<User> getAll();

//    void update(UserTo user);

    void update(User user);
}
