package com.cartrack.autodialer.web;

import com.cartrack.autodialer.domain.ClientList;
import com.cartrack.autodialer.domain.OriginateParam;
import com.cartrack.autodialer.domain.Task;
import com.cartrack.autodialer.service.ClientListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by vinner on 13.11.2015.
 */
@RestController
@RequestMapping("ajax/admin/asterisk")
public class AjaxOriginateParamController extends AbstractOriginateParamController {


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<OriginateParam> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public OriginateParam get(@PathVariable("id") int id){
        return super.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void updateOrCreate(@RequestParam("id") int id,
                               @RequestParam("name") String name,
                               @RequestParam("context") String context,
                               @RequestParam("extension") String extension,
                               @RequestParam("priority") int priority,
                               @RequestParam("async") boolean async,
                               @RequestParam("timeout") long timeout,
                               @RequestParam("var1") String var1,
                               @RequestParam("var2") String var2){


        OriginateParam originateParam = new OriginateParam(id, name, context, extension, priority, async, timeout, var1, var2);
        if (id == 0) {
            super.create(originateParam);
        } else {
            super.update(originateParam, id);
        }
    }


}
