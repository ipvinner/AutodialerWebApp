package com.cartrack.autodialer.service;

import com.cartrack.autodialer.TestData;
import com.cartrack.autodialer.domain.User;
import com.cartrack.autodialer.matcher.ModelMatcher;
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
 * Created by vinner on 30.12.2015.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-asterisk.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest {

    public static final ModelMatcher<User, String> MATCHER = new ModelMatcher<>(User::toString);

    @Autowired
    protected UserService service;


    @Test
    public void testDelete() throws Exception {
        service.delete(1);
        MATCHER.assertCollectionEquals(Arrays.asList(TestData.MANAGER, TestData.USER), service.getAll());
    }

    @Test
    public void testGet() throws Exception {
        User user = service.get(1);
        MATCHER.assertEquals(TestData.ADMIN, user);

    }

    @Test
    public void testGetAll() throws Exception {
        List<User> userList = service.getAll();
        MATCHER.assertCollectionEquals(Arrays.asList(TestData.ADMIN, TestData.MANAGER, TestData.USER), userList);
    }

    @Test
    public void testSave() throws Exception {
        User newUser = new User(null, "newUser", "g8keeper", "ROLE_ADMIN");
        User created = service.save(newUser);
        MATCHER.assertCollectionEquals(Arrays.asList(TestData.ADMIN, TestData.MANAGER, TestData.USER, TestData.NEWUSER), service.getAll());

    }

    @Test
    public void testUpdate() throws Exception {
        User updated = new User(TestData.ADMIN.getId(), "UPDATEName", "g8keeper", "ROLE_ADMIN");
        service.update(updated);
        MATCHER.assertEquals(updated, service.get(1));
    }

    @Test
    public void testGetUserByLogin(){
        User admin = service.getByLogin("admin");
        MATCHER.assertEquals(admin, service.get(1));
    }
}