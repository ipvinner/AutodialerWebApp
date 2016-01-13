package com.cartrack.autodialer.repository.jdbc;

import com.cartrack.autodialer.domain.Role;
import com.cartrack.autodialer.domain.User;
import com.cartrack.autodialer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by vinner on 30.12.2015.
 */
@Repository
public class JdbcUserRepository implements UserRepository {

    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);

    private  final RowMapper<User> GET_ROW_MAPPER = (rs, rowNum) ->
            new User(rs.getInt("id"), rs.getString("login"), rs.getString("password_hash"), rs.getString("role"));

    @Autowired
    private JdbcTemplate jdbcTemplate;


    private SimpleJdbcInsert insertUser;

    @Override
    public User save(User user) {
        if(user.isNew()){
            jdbcTemplate.update("INSERT INTO users (login, password_hash, role) VALUES (?, ?, ?)",
                    user.getLogin(), user.getPasswordHash(), user.getRole());
        }else{
            jdbcTemplate.update("UPDATE users SET login = ?, password_hash = ?, role = ? WHERE id = ?",
                    user.getLogin(), user.getPasswordHash(), user.getRole(), user.getId());
        }
        return user;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id) != 0;
    }

    @Override
    public User get(int id) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE id=?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public User getByLogin(String login) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE login=?", GET_ROW_MAPPER, login);
//        User user = DataAccessUtils.singleResult(users);
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public List<User> getAll() {
        List<User> users = jdbcTemplate.query("SELECT * FROM users", ROW_MAPPER);
        return users;
    }




}
