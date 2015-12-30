package com.cartrack.autodialer.web.user;

import com.cartrack.autodialer.LoggerWrapper;
import com.cartrack.autodialer.domain.Task;
import com.cartrack.autodialer.domain.User;
import com.cartrack.autodialer.service.TaskService;
import com.cartrack.autodialer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * Created by vinner on 13.11.2015.
 */
public class AbstractUserController {

    protected final LoggerWrapper LOG = LoggerWrapper.get(getClass());

    @Autowired
    private UserService service;

    public List<User> getAll() {
        LOG.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        LOG.info("get " + id);
        return service.get(id);
    }

    public User create(User user) {
        user.setId(null);
        LOG.info("create " + user);
        return service.save(user);
    }

    public void delete(int id) {
        LOG.info("delete " + id);
        service.delete(id);
    }

    public void update(User user, int id) {
        user.setId(id);
        LOG.info("update " + user);
        service.update(user);
    }

}
