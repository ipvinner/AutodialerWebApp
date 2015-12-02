package com.cartrack.autodialer.service;

import com.cartrack.autodialer.domain.Task;

import java.util.List;

/**
 * Created by vinner on 13.11.2015.
 */
public interface TaskService {

    List<Task> getAll();

    Task get(int id);

    void delete(int id);

    Task save(Task task);

    Task update(Task task);

    void start(Task task);
}
