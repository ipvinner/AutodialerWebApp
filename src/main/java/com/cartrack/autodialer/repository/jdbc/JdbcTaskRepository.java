package com.cartrack.autodialer.repository.jdbc;

import com.cartrack.autodialer.domain.Task;
import com.cartrack.autodialer.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vinner on 13.11.2015.
 */
@Repository
@Transactional(readOnly = true)
public class JdbcTaskRepository implements TaskRepository{

    private static final BeanPropertyRowMapper<Task> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Task.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Task> getAll() {
        return jdbcTemplate.query("SELECT * FROM task", ROW_MAPPER);
    }

    @Override
    public Task save(Task task) {
        if(task.isNew()){
            jdbcTemplate.update("INSERT INTO task (name, client_list_id, active, originate_param_id) VALUES (?, ?, ?, ?)",
                    task.getName(), task.getClientList().getId(), task.isActive(), task.getOriginateParam().getId());
        }else{
            jdbcTemplate.update("UPDATE task SET name = ?, client_list_id = ?, active = ?, originate_param_id = ? WHERE id = ?",
                    task.getName(), task.getClientList().getId(), task.isActive(), task.getOriginateParam().getId(), task.getId());
        }
        return task;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM task WHERE id=?", id) !=0;
    }

    @Override
    public Task get(int id) {
        List<Task> tasks = jdbcTemplate.query("SELECT * FROM task WHERE id = ?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(tasks);
    }
}
