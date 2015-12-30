package com.cartrack.autodialer.service;

import com.cartrack.autodialer.domain.User;
import com.cartrack.autodialer.repository.UserRepository;
import com.cartrack.autodialer.util.NotFoundException;
import com.cartrack.autodialer.util.exception.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vinner on 30.12.2015.
 */
@Service
public class UserServiceImpl implements UserService {

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
    public List<User> getAll() {
        return repository.getAll();
    }


    public void update(User user) {
        repository.save(user);
    }

}
