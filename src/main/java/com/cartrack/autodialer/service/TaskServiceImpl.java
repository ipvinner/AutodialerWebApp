package com.cartrack.autodialer.service;

import com.cartrack.autodialer.asterisk.AsteriskHelper;
import com.cartrack.autodialer.domain.Client;
import com.cartrack.autodialer.domain.OriginateParam;
import com.cartrack.autodialer.domain.Task;
import com.cartrack.autodialer.repository.ClientRepository;
import com.cartrack.autodialer.repository.OriginateParamRepository;
import com.cartrack.autodialer.repository.TaskRepository;
import com.cartrack.autodialer.util.exception.ExceptionUtil;
import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.TimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by vinner on 13.11.2015.
 */
@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private AsteriskHelper asteriskHelper;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    OriginateParamRepository originateParamRepository;

    @Override
    public List<Task> getAll() {
        return taskRepository.getAll();
    }

    @Override
    public Task get(int id) {
        return ExceptionUtil.check(taskRepository.get(id), id);
    }

    @Override
    public void delete(int id) {
        ExceptionUtil.check(taskRepository.delete(id), id);
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task update(Task task) {
        return ExceptionUtil.check(taskRepository.save(task), task.getId());
    }

    @Override
    public void start(Task task) {

        List<Client> clients = clientRepository.getByList(task.getClientList().getId());


        for (Client client : clients) {
            try {
                asteriskHelper.call(task.getOriginateParam().getTrunk(), client.getPhoneNumber(), task.getOriginateParam().getContext(), task.getOriginateParam().getExten(),
                        task.getOriginateParam().getPriority(), task.getOriginateParam().getTimeout(), task.getOriginateParam().isAsync(),
                        task.getOriginateParam().getVar1(), task.getOriginateParam().getVar2());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (AuthenticationFailedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
