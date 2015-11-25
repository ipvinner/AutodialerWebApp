package com.cartrack.autodialer.repository;

import com.cartrack.autodialer.domain.OriginateParam;

import java.util.List;

/**
 * Created by vinner on 25.11.2015.
 */
public interface OriginateParamRepository {

    List<OriginateParam> getAll();

    OriginateParam get(int id);

    OriginateParam save(OriginateParam originateParam);

    boolean delete(int id);
}
