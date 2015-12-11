package com.cartrack.autodialer.repository.jdbc;

import com.cartrack.autodialer.domain.CallResult;
import com.cartrack.autodialer.repository.CallResultRepository;
import com.cartrack.autodialer.repository.ClientRepository;
import com.cartrack.autodialer.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by vinner on 09.12.2015.
 */
@Repository
@Transactional(readOnly = true)
public class JdbcCallResultRepository implements CallResultRepository {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ClientRepository clientRepository;

    private final RowMapper<CallResult> ROW_MAPPER =
            (rs, rowNum) ->
                    new CallResult(rs.getInt("id"), rs.getTimestamp("datetime").toLocalDateTime(),
                            rs.getString("result"), rs.getString("reason"), taskRepository.get(rs.getInt("task_id")), clientRepository.get(rs.getInt("client_id")));

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<CallResult> getAll() {

        return jdbcTemplate.query("SELECT * FROM call_result", ROW_MAPPER);
    }

    @Override
    public List<CallResult> getByTaskId(int taskId) {
        return jdbcTemplate.query("SELECT * FROM call_result WHERE task_id = ?", ROW_MAPPER, taskId);
    }

    @Override
    public List<CallResult> getBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return jdbcTemplate.query("SELECT * FROM call_result WHERE datetime BETWEEN ? AND ? ORDER BY datetime DESC",
                ROW_MAPPER, Timestamp.valueOf(startDate), Timestamp.valueOf(endDate));
    }

    @Override
    public CallResult save(CallResult callResult) {
        if(callResult.isNew()){
            jdbcTemplate.update("INSERT INTO call_result (datetime, result, reason, task_id, client_id) VALUES (?, ?, ?, ?, ?)",
                    Timestamp.valueOf(callResult.getDateTime()), callResult.getResult(), callResult.getReason(), callResult.getTask().getId(), callResult.getClient().getId());
        }else{
            jdbcTemplate.update("UPDATE call_result SET datetime = ?, result = ?, reason = ?, task_id = ?, client_id = ? WHERE id = ?",
                    Timestamp.valueOf(callResult.getDateTime()), callResult.getResult(), callResult.getReason(), callResult.getTask().getId(), callResult.getClient().getId(), callResult.getId());
        }
        return callResult;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM call_result WHERE id=?", id) !=0;
    }


}
