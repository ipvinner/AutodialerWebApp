package com.cartrack.autodialer.repository;

import com.cartrack.autodialer.domain.CallResult;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Created by vinner on 09.12.2015.
 */
public interface CallResultRepository {

    List<CallResult> getAll();

    List<CallResult> getByTaskId(int taskId);

    CallResult save(CallResult callResult);

    boolean delete(int id);

    List<CallResult> getBetween(LocalDateTime startDate, LocalDateTime endDate);


}
