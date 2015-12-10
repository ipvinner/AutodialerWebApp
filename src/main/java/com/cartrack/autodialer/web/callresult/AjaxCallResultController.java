package com.cartrack.autodialer.web.callresult;

import com.cartrack.autodialer.domain.CallResult;
import com.cartrack.autodialer.domain.OriginateParam;
import com.cartrack.autodialer.web.asterisk.AbstractOriginateParamController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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


}
