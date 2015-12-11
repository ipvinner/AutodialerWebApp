package com.cartrack.autodialer.service;

import com.cartrack.autodialer.domain.CallResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

/**
 * Created by vinner on 09.12.2015.
 */
public interface CallResultService {

    List<CallResult> getByTaskId(int taskId);

    List<CallResult> getAll();

    default List<CallResult> getBetweenDates(LocalDate startDate, LocalDate endDate) {
        return getBetween(LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(endDate, LocalTime.MAX));
    }

    List<CallResult> getBetween(LocalDateTime startDate, LocalDateTime endDate);

    CallResult save(CallResult callResult);

    CallResult update(CallResult callResult);

    void delete(int id);


}
