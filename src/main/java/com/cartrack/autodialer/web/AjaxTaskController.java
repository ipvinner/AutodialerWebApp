package com.cartrack.autodialer.web;

import com.cartrack.autodialer.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by vinner on 13.11.2015.
 */
@RestController
@RequestMapping("ajax/admin/tasks")
public class AjaxTaskController extends AbstractTaskController {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Task> getAll() {
        return super.getAll();
    }


}
