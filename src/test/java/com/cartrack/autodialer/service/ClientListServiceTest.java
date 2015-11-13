package com.cartrack.autodialer.service;

import com.cartrack.autodialer.ClientTestData;
import com.cartrack.autodialer.TestData;
import com.cartrack.autodialer.domain.ClientList;
import com.cartrack.autodialer.matcher.ModelMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by vinner on 05.11.2015.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class ClientListServiceTest {

    public static final ModelMatcher<ClientList, String> MATCHER = new ModelMatcher<>(ClientList::toString);

    @Autowired
    protected ClientListService service;

    @Test
    public void testGetAll() throws Exception {
        Collection<ClientList> clientsLists = service.getAll();
        MATCHER.assertCollectionEquals(Arrays.asList(TestData.CLIENT_LIST1, TestData.CLIENT_LIST2, TestData.CLIENT_LIST3, TestData.CLIENT_LIST4), clientsLists);
    }


    @Test
    public void testGetById() throws Exception {
        ClientList clientList = service.getById(1);
        MATCHER.assertEquals(ClientTestData.CLIENT_LIST1, clientList);
    }
}