package com.cartrack.autodialer.service;

import com.cartrack.autodialer.LoggedUser;
import com.cartrack.autodialer.domain.User;
import com.cartrack.autodialer.repository.UserRepository;
import com.cartrack.autodialer.util.NotFoundException;
import com.cartrack.autodialer.util.exception.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vinner on 30.12.2015.
 */
@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }


    @Override
    public void delete(int id) {
        ExceptionUtil.check(repository.delete(id), id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return ExceptionUtil.check(repository.get(id), id);
    }

    @Override
    public User getByLogin(String login) throws NotFoundException {
        return ExceptionUtil.check(repository.getByLogin(login), login);
    }


    @Override
    public List<User> getAll() {
        return repository.getAll();
    }


    public void update(User user) {
        repository.save(user);
    }

    @Override
    public LoggedUser loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = repository.getByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User " + login + " is not found");
        }

        LoggedUser loggedUser = new LoggedUser(user);
        loggedUser.addRole(user.getRole());
        return loggedUser;
    }
}
