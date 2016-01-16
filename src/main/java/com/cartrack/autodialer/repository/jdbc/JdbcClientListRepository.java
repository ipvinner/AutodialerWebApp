package com.cartrack.autodialer.repository.jdbc;

import com.cartrack.autodialer.domain.Client;
import com.cartrack.autodialer.domain.ClientList;
import com.cartrack.autodialer.repository.ClientListRepository;
import com.cartrack.autodialer.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by vinner on 05.11.2015.
 */
@Repository
public class JdbcClientListRepository implements ClientListRepository {
    private static final BeanPropertyRowMapper<ClientList> ROW_MAPPER = BeanPropertyRowMapper.newInstance(ClientList.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<ClientList> getAll() {
        return jdbcTemplate.query("SELECT * FROM list", ROW_MAPPER);
    }

    @Override
    public ClientList get(int id) {
        List<ClientList> lists = jdbcTemplate.query("SELECT * FROM list WHERE id = ?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(lists);
    }

    @Override
    @Transactional
    public void createWithListOfClients(ClientList clientList, List<Client> clientsList) {

//        jdbcTemplate.update("INSERT INTO list (name, description) VALUES (?, ?)",
//                clientList.getName(), clientList.getDescription());
        String insert = "INSERT INTO list (name, description) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int row = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(insert, new String[] {"id"});
                ps.setString(1, clientList.getName());
                ps.setString(2, clientList.getName());
                return ps;
            }
        }, keyHolder);
            long listId = keyHolder.getKey().longValue();


        String sql = "INSERT INTO client (firstname, lastname, phone_number, email, clients_list_id) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, clientsList.get(i).getFirstName());
                ps.setString(2, clientsList.get(i).getLastName());
                ps.setString(3, clientsList.get(i).getPhoneNumber());
                ps.setString(4, clientsList.get(i).getEmail());
                ps.setLong(5, listId);
            }

            @Override
            public int getBatchSize() {
                return clientsList.size();
            }
        });

    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM list WHERE id=?", id) !=0;
    }
}
