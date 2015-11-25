package com.cartrack.autodialer.repository.jdbc;

import com.cartrack.autodialer.domain.OriginateParam;
import com.cartrack.autodialer.repository.OriginateParamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vinner on 25.11.2015.
 */
@Repository
@Transactional(readOnly = true)
public class JdbcOriginateParamRepository implements OriginateParamRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final BeanPropertyRowMapper<OriginateParam> ROW_MAPPER = BeanPropertyRowMapper.newInstance(OriginateParam.class);

    @Override
    public List<OriginateParam> getAll() {
        List<OriginateParam> originateParamList = jdbcTemplate.query("SELECT * FROM originate_param", ROW_MAPPER);
        return jdbcTemplate.query("SELECT * FROM originate_param", ROW_MAPPER);
    }

    @Override
    public OriginateParam get(int id) {
        List<OriginateParam> originateParamList = jdbcTemplate.query("SELECT * FROM originate_param WHERE id = ?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(originateParamList);
    }

    @Override
    @Transactional
    public OriginateParam save(OriginateParam originateParam) {
        if(originateParam.isNew()){
            jdbcTemplate.update("INSERT INTO originate_param (name, context, extension, priority, async, timeout, var1, var2) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                    originateParam.getName(), originateParam.getContext(), originateParam.getExtension(), originateParam.getPriority(),
                    originateParam.isAsync(), originateParam.getTimeout(), originateParam.getVar1(), originateParam.getVar2());
        }else{
            jdbcTemplate.update("UPDATE originate_param SET name = ?, context = ?, extension = ?, priority = ?, async = ?, timeout = ?, var1 = ?, var2 = ? WHERE id = ?",
                    originateParam.getName(), originateParam.getContext(), originateParam.getExtension(), originateParam.getPriority(),
                    originateParam.isAsync(), originateParam.getTimeout(), originateParam.getVar1(), originateParam.getVar2(), originateParam.getId());
        }
        return originateParam;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM originate_param WHERE id=?", id) !=0;
    }
}
