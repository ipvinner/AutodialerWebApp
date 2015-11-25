package com.cartrack.autodialer.web;

import com.cartrack.autodialer.LoggerWrapper;
import com.cartrack.autodialer.domain.OriginateParam;
import com.cartrack.autodialer.domain.Task;
import com.cartrack.autodialer.service.OriginateParamService;
import com.cartrack.autodialer.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * Created by vinner on 13.11.2015.
 */
public class AbstractOriginateParamController {

    protected final LoggerWrapper LOG = LoggerWrapper.get(getClass());

    @Autowired
    private OriginateParamService service;

    public Collection<OriginateParam> getAll(){
        LOG.info("getAll originate params");
        return service.getAll();
    }

    public OriginateParam get(int id){
        LOG.info("get client by id: " + id);
        return service.get(id);
    }

    public void delete(int id){
        LOG.info("delete + " + id);
        service.delete(id);
    }

    public OriginateParam create(OriginateParam originateParam) {
        originateParam.setId(null);
        LOG.info("create " + originateParam);
        return service.save(originateParam);
    }

    public OriginateParam update(OriginateParam originateParam, int id) {
        originateParam.setId(id);
        LOG.info("update originate param + " + originateParam);
        return service.update(originateParam);
    }
}
