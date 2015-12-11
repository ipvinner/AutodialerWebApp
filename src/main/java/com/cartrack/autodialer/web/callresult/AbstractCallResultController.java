package com.cartrack.autodialer.web.callresult;

import com.cartrack.autodialer.LoggerWrapper;
import com.cartrack.autodialer.domain.CallResult;
import com.cartrack.autodialer.service.CallResultService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by vinner on 13.11.2015.
 */
public class AbstractCallResultController {

    protected final LoggerWrapper LOG = LoggerWrapper.get(getClass());

    @Autowired
    private CallResultService service;

    public List<CallResult> getAll(){
        LOG.info("getAll() CallResult");
        return service.getAll();
    }

    public List<CallResult> getByTaskId(int id){
        LOG.info("getByTaskId CallResults");
        return service.getByTaskId(id);
    }

    public List<CallResult> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime){
        LOG.info("getBetween dates {} - {} for time {}", startDate, endDate, startTime, endTime);
        return service.getBetweenDates(startDate, endDate);
    }
}
