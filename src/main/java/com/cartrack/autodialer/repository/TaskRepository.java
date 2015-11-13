package com.cartrack.autodialer.repository;

import com.cartrack.autodialer.domain.Task;

import java.util.List;

/**
 * Created by vinner on 13.11.2015.
 */
public interface TaskRepository {

    List<Task> getAll();

    Task save(Task client);

    boolean delete(int id);

    Task get(int id);
}
