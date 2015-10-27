package com.cartrack.autodialer.repository.jdbc;

import com.cartrack.autodialer.domain.Client;
import com.cartrack.autodialer.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vinner on 29.09.2015.
 */
@Repository
public class JdbcClientRepository implements ClientRepository {

    private static final BeanPropertyRowMapper<Client> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Client.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Client> getByList(int listID) {
        return jdbcTemplate.query("SELECT * FROM client WHERE clients_list_id = ?", ROW_MAPPER, listID);
    }

    @Override
    public Client save(Client client) {
        if(client.isNew()){
            jdbcTemplate.update("INSERT INTO client (firstname, lastname, phone_number, email, clients_list_id) VALUES (?, ?, ?, ?, ?)",
                    client.getFirstName(), client.getLastName(), client.getPhoneNumber(), client.getEmail(), client.getClientList().getId());
        }else{
            jdbcTemplate.update("UPDATE client SET firstname = ?, lastname = ?, phone_number = ?, email = ? WHERE id = ?",
                client.getFirstName(), client.getLastName(), client.getPhoneNumber(), client.getEmail(), client.getId());
        }
        return client;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM client WHERE id=?", id) !=0;
    }

    @Override
    public Client get(int id) {
        List<Client> clients = jdbcTemplate.query("SELECT id, firstname, lastname, phone_number, email FROM client WHERE id = ?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(clients);
    }

    @Override
    public void deleteAllByList(int listId) {
         jdbcTemplate.update("DELETE FROM client WHERE clients_list_id=?", listId);
    }
}
