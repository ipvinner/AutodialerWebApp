package com.cartrack.autodialer.web.callresult;

import com.cartrack.autodialer.domain.CallResult;
import com.cartrack.autodialer.domain.OriginateParam;
import com.cartrack.autodialer.util.TimeUtil;
import com.cartrack.autodialer.web.asterisk.AbstractOriginateParamController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

/**
 * Created by vinner on 13.11.2015.
 */
@RestController
@RequestMapping("ajax/admin/results")
public class AjaxCallResultController extends AbstractCallResultController {


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CallResult> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CallResult> getByTaskId(@PathVariable("id") int id){
        return super.getByTaskId(id);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CallResult> getBetween(
            @RequestParam(value = "startDate", required = false) LocalDate startDate, @RequestParam(value = "startTime", required = false) LocalTime startTime,
            @RequestParam(value = "endDate", required = false) LocalDate endDate, @RequestParam(value = "endTime", required = false) LocalTime endTime) {
        return super.getBetween(startDate != null ? startDate : TimeUtil.MIN_DATE, startTime != null ? startTime : LocalTime.MIN,
                endDate != null ? endDate : TimeUtil.MAX_DATE, endTime != null ? endTime : LocalTime.MAX);
    }


}
