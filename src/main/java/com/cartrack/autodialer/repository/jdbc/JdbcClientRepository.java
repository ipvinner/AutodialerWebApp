package com.cartrack.autodialer.repository.jdbc;

import com.cartrack.autodialer.domain.Client;
import com.cartrack.autodialer.repository.ClientListRepository;
import com.cartrack.autodialer.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vinner on 29.09.2015.
 */
@Repository
@Transactional(readOnly = true)
public class JdbcClientRepository implements ClientRepository {

    @Autowired
    private ClientListRepository clientListRepository;
//    private static final BeanPropertyRowMapper<Client> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Client.class);
    private  final RowMapper<Client> ROW_MAPPER = (rs, rowNum) ->
        new Client(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("phone_number"),
                rs.getString("email"), clientListRepository.get(rs.getInt("clients_list_id")));

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Client> getByList(int listID) {
        return jdbcTemplate.query("SELECT * FROM client WHERE clients_list_id = ?", ROW_MAPPER, listID);
    }

    @Override
    public List<Client> getAll() {
        return jdbcTemplate.query("SELECT * FROM client", ROW_MAPPER);
    }

    @Override
    @Transactional
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
    @Transactional
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM client WHERE id=?", id) !=0;
    }

    @Override
    public Client get(int id) {
        List<Client> clients = jdbcTemplate.query("SELECT id, firstname, lastname, phone_number, email, clients_list_id FROM client WHERE id = ?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(clients);
    }

    @Override
    public void deleteAllByList(int listId) {
         jdbcTemplate.update("DELETE FROM client WHERE clients_list_id=?", listId);
    }
}
