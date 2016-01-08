package com.cartrack.autodialer.web.task;

import com.cartrack.autodialer.domain.ClientList;
import com.cartrack.autodialer.domain.OriginateParam;
import com.cartrack.autodialer.domain.Task;
import com.cartrack.autodialer.service.ClientListService;
import com.cartrack.autodialer.service.OriginateParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by vinner on 13.11.2015.
 */
@RestController
@RequestMapping("ajax/admin/tasks")
public class AjaxTaskController extends AbstractTaskController {

    @Autowired
    ClientListService clientListService;

    @Autowired
    OriginateParamService originateParamService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Task> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Task get(@PathVariable("id") int id){
        return super.get(id);
    }

    @RequestMapping(value = "/play/{id}", method = RequestMethod.GET)
    public void startTask(@PathVariable("id") int id){
        super.start(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void updateOrCreate(@RequestParam("id") int id,
                               @RequestParam("name") String name,
                               @RequestParam("client_list_id") int client_list_id,
                               @RequestParam("active") boolean active,
                               @RequestParam("originate_param_id") int originate_param_id){

        ClientList clientList = clientListService.get(client_list_id);
        Task task = new Task(id, name, clientList, active, new OriginateParam());
        if (id == 0) {
            super.create(task);
        } else {
            super.update(task, id);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestParam("task_name") String name,
                               @RequestParam("clients_list_id") int client_list_id,
                               @RequestParam("task_active") boolean active,
                               @RequestParam("task_originate_param_id") int originate_param_id){
        Task task = new Task(null, name, clientListService.get(client_list_id), active, originateParamService.get(originate_param_id));
        super.create(task);

    }


}
