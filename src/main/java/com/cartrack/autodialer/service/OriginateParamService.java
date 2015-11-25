package com.cartrack.autodialer.service;

import com.cartrack.autodialer.domain.OriginateParam;

import java.util.List;

/**
 * Created by vinner on 25.11.2015.
 */
public interface OriginateParamService {

    List<OriginateParam> getAll();

    OriginateParam save(OriginateParam originateParam);

    OriginateParam update(OriginateParam originateParam);

    void delete(int id);

    OriginateParam get(int id);

}
