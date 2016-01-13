package com.cartrack.autodialer.web;

import com.cartrack.autodialer.service.ClientListService;
import com.cartrack.autodialer.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by vinner on 05.11.2015.
 */
@Controller
public class RootController {

    @Autowired
    private ClientService service;

    @Autowired
    private ClientListService serviceLists;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model,
                        @RequestParam(value = "error", required = false) boolean error,
                        @RequestParam(value = "message", required = false) String message) {

        model.put("error", error);
        model.put("message", message);
        return "login";
    }

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

    @RequestMapping(value = "/addList", method = RequestMethod.GET)
    public String addList(Model model) {
        return "addList";
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public String taskList() {
        return "tasks";
    }

    @RequestMapping(value = "/asterisk", method = RequestMethod.GET)
    public String originateParamsList() {
        return "asterisk";
    }

    @RequestMapping(value = "/results", method = RequestMethod.GET)
    public String callResultList() {
        return "results";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String userList() {
        return "users";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile() {
        return "profile";
    }
}
