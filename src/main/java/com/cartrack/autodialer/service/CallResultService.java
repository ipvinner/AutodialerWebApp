package com.cartrack.autodialer.service;

import com.cartrack.autodialer.domain.CallResult;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by vinner on 09.12.2015.
 */
public interface CallResultService {

    List<CallResult> getByTaskId(int taskId);

    List<CallResult> getAll();

    List<CallResult> getBetween(LocalDateTime startDate, LocalDateTime endDate);

    CallResult save(CallResult callResult);

    CallResult update(CallResult callResult);

    void delete(int id);


}
