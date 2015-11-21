package com.cartrack.autodialer.service;

import com.cartrack.autodialer.domain.Client;
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
import java.util.regex.Matcher;

import static org.junit.Assert.*;
import com.cartrack.autodialer.ClientTestData;
/**
 * Created by vinner on 29.09.2015.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class ClientServiceTest {

    public static final ModelMatcher<Client, String> MATCHER = new ModelMatcher<>(Client::toString);

    @Autowired
    protected ClientService service;

    @Test
    public void testGet() throws Exception {
        Client client = service.get(1);
        MATCHER.assertEquals(ClientTestData.CLIENT1, client);
    }

    @Test
    public void testGetByList() throws Exception {
        Collection<Client> clients = service.getByList(1);
        MATCHER.assertCollectionEquals(Arrays.asList(ClientTestData.CLIENT1, ClientTestData.CLIENT2, ClientTestData.CLIENT3, ClientTestData.CLIENT4), clients);
    }

    @Test
    public void testSave() throws Exception {

        Client client = new Client(null, "New Client", "lastname", "phoneNew", "emailnew", ClientTestData.CLIENT_LIST1);
        Client created = service.save(client);

        MATCHER.assertCollectionEquals(Arrays.asList(ClientTestData.CLIENT1, ClientTestData.CLIENT2, ClientTestData.CLIENT3, ClientTestData.CLIENT4, ClientTestData.CLIENTNEW), service.getByList(1));
    }

    @Test
    public void testUpdate() throws Exception {
        Client client = new Client(ClientTestData.CLIENT1);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(1);
        MATCHER.assertCollectionEquals(Arrays.asList(ClientTestData.CLIENT2, ClientTestData.CLIENT3, ClientTestData.CLIENT4), service.getByList(1));
    }



    @Test
    public void testDeleteAllByList() throws Exception {
        service.deleteAllByList(1);
        MATCHER.assertCollectionEquals(Arrays.asList(), service.getByList(1));
    }
}