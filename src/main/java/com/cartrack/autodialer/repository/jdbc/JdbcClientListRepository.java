package com.cartrack.autodialer.repository.jdbc;

import com.cartrack.autodialer.domain.Client;
import com.cartrack.autodialer.domain.ClientList;
import com.cartrack.autodialer.repository.ClientListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vinner on 05.11.2015.
 */
@Repository
public class JdbcClientListRepository implements ClientListRepository {
    private static final BeanPropertyRowMapper<ClientList> ROW_MAPPER = BeanPropertyRowMapper.newInstance(ClientList.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ClientList> getAll() {
        return jdbcTemplate.query("SELECT * FROM list", ROW_MAPPER);
    }

    @Override
    public ClientList get(int id) {
        List<ClientList> lists = jdbcTemplate.query("SELECT * FROM list WHERE id = ?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(lists);
    }
}
