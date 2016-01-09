package com.cartrack.autodialer.service;

import com.cartrack.autodialer.ClientTestData;
import com.cartrack.autodialer.TestData;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by vinner on 05.11.2015.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-asterisk.xml"

})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class ClientListServiceTest {

    public static final ModelMatcher<ClientList, String> MATCHER = new ModelMatcher<>(ClientList::toString);

    @Autowired
    protected ClientListService service;

    @Autowired
    private ClientService clientService;

    @Test
    public void testGetAll() throws Exception {
        Collection<ClientList> clientsLists = service.getAll();
        MATCHER.assertCollectionEquals(Arrays.asList(TestData.CLIENT_LIST1, TestData.CLIENT_LIST2, TestData.CLIENT_LIST3), clientsLists);
    }


    @Test
    public void testGetById() throws Exception {
        ClientList clientList = service.get(1);
        MATCHER.assertEquals(ClientTestData.CLIENT_LIST1, clientList);
    }

    @Test
    public void testCreateWithListOfClients(){
        ClientList clientList = new ClientList(null, "new", "new");
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(null, "Феофанов", "Андрей", "+38050325678", "6@urk.net", ClientTestData.CLIENT_LIST_NEW));
        clients.add(new Client(null, "Сергеев", "Дмитрий", "+38055325678", "2@mail.ru", ClientTestData.CLIENT_LIST_NEW));
        clients.add(new Client(null, "Наумова", "Дмитрий", "+380738625020", "naumov@mail.ru", ClientTestData.CLIENT_LIST_NEW));
        service.createWithListOfClients(clientList, clients );

        MATCHER.assertCollectionEquals(Arrays.asList(ClientTestData.CLIENT_LIST1, ClientTestData.CLIENT_LIST2, ClientTestData.CLIENT_LIST3,
                ClientTestData.CLIENT_LIST_NEW), service.getAll());

    }
}