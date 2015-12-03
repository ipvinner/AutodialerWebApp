package com.cartrack.autodialer.service;

import com.cartrack.autodialer.ClientTestData;
import com.cartrack.autodialer.domain.Task;
import com.cartrack.autodialer.matcher.ModelMatcher;
import com.cartrack.autodialer.util.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by vinner on 13.11.2015.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-asterisk.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class TaskServiceTest {

    public static final ModelMatcher<Task, String> MATCHER = new ModelMatcher<>(Task::toString);

    @Autowired
    protected TaskService service;

    @Test
    public void testGetAll() throws Exception {
        List<Task> taskList = service.getAll();
        MATCHER.assertCollectionEquals(Arrays.asList(ClientTestData.TASK1, ClientTestData.TASK2, ClientTestData.TASK3, ClientTestData.TASK4, ClientTestData.TASK5), taskList);
    }

    @Test
    public void testGet() throws Exception {
        Task task = service.get(1);
        MATCHER.assertEquals(ClientTestData.TASK1, task);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(1);
        MATCHER.assertCollectionEquals(Arrays.asList(ClientTestData.TASK2, ClientTestData.TASK3, ClientTestData.TASK4, ClientTestData.TASK5), service.getAll());
    }

    @Test
    public void testSave() throws Exception {
        Task newtask = new Task(null, "newTask", ClientTestData.CLIENT_LIST1, true, ClientTestData.ORIGINATE_PARAM2);
        Task created = service.save(newtask);
        MATCHER.assertCollectionEquals(Arrays.asList(ClientTestData.TASK1, ClientTestData.TASK2, ClientTestData.TASK3, ClientTestData.TASK4, ClientTestData.TASK5, ClientTestData.TASKNEW), service.getAll());

    }

    @Test
    public void testUpdate() throws Exception {
        Task updated = new Task(ClientTestData.TASK1.getId(), "updated task", ClientTestData.TASK1.getClientList(), ClientTestData.TASK1.isActive(), ClientTestData.TASK1.getOriginateParam());
        service.update(updated);
        MATCHER.assertEquals(updated, service.get(1));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(10000);
    }
}