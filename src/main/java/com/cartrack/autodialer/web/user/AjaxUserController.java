package com.cartrack.autodialer.web.user;

import com.cartrack.autodialer.LoggedUser;
import com.cartrack.autodialer.domain.*;
import com.cartrack.autodialer.service.ClientListService;
import com.cartrack.autodialer.util.PasswordUtil;
import com.cartrack.autodialer.web.task.AbstractTaskController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * Created by vinner on 13.11.2015.
 */
@RestController
@RequestMapping("ajax/admin/users")
public class AjaxUserController extends AbstractUserController{

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void update(@RequestParam("id") int id,
                       @RequestParam("login") String login,
                       @RequestParam("password") String password,
                       @RequestParam("role") String role) {

        User user = new User(id, login.toLowerCase(), PasswordUtil.encode(password), role);
        if (id == 0) {
            super.create(user);
        } else {
            super.update(user, id);
        }
    }

}
