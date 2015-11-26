package com.cartrack.autodialer.web;

import com.cartrack.autodialer.service.ClientListService;
import com.cartrack.autodialer.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by vinner on 05.11.2015.
 */
@Controller
public class RootController {

    @Autowired
    private ClientService service;

    @Autowired
    private ClientListService serviceLists;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public String userList(Model model) {
        model.addAttribute("clientsLists", serviceLists.getAll());
        return "clients";
    }

    @RequestMapping(value = "/clientsList", method = RequestMethod.GET)
    public String clientList(Model model) {
        model.addAttribute("clientsLists", serviceLists.getAll());
        return "clientsList";
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public String taskList() {
        return "tasks";
    }

    @RequestMapping(value = "/asterisk", method = RequestMethod.GET)
    public String originateParamsList() {
        return "asterisk";
    }
}
