package com.cartrack.autodialer.service;

import com.cartrack.autodialer.domain.CallResult;
import com.cartrack.autodialer.repository.CallResultRepository;
import com.cartrack.autodialer.util.exception.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by vinner on 09.12.2015.
 */
@Service
public class CallResultServiceImpl implements CallResultService {

    @Autowired
    private CallResultRepository callResultRepository;

    @Override
    public List<CallResult> getByTaskId(int taskId) {
        return callResultRepository.getByTaskId(taskId);
    }

    @Override
    public List<CallResult> getAll() {
        return callResultRepository.getAll();
    }

    @Override
    public List<CallResult> getBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return callResultRepository.getBetween(startDate, endDate);
    }

    @Override
    public CallResult save(CallResult callResult) {
        return callResultRepository.save(callResult);
    }

    @Override
    public CallResult update(CallResult callResult) {
        return ExceptionUtil.check(callResultRepository.save(callResult), callResult.getId() );
    }

    @Override
    public void delete(int id) {
        ExceptionUtil.check(callResultRepository.delete(id), id);
    }
}
