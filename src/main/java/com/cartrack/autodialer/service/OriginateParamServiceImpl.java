package com.cartrack.autodialer.service;

import com.cartrack.autodialer.LoggerWrapper;
import com.cartrack.autodialer.domain.OriginateParam;
import com.cartrack.autodialer.repository.OriginateParamRepository;
import com.cartrack.autodialer.util.exception.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vinner on 25.11.2015.
 */
@Service
public class OriginateParamServiceImpl implements OriginateParamService {

    @Autowired
    private OriginateParamRepository repository;

    private static final LoggerWrapper LOG = LoggerWrapper.get(OriginateParamServiceImpl.class);

    @Override
    public List<OriginateParam> getAll() {
        List<OriginateParam> list = repository.getAll();
        return repository.getAll();
    }

    @Override
    public OriginateParam get(int id) {
        return ExceptionUtil.check(repository.get(id), id);
    }

    @Override
    public OriginateParam save(OriginateParam originateParam) {
        return repository.save(originateParam);
    }

    @Override
    public OriginateParam update(OriginateParam originateParam) {
        return ExceptionUtil.check(repository.save(originateParam), originateParam.getId());
    }

    @Override
    public void delete(int id) {
        ExceptionUtil.check(repository.delete(id), id);
    }


}
