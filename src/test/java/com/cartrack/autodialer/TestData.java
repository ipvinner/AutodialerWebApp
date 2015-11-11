package com.cartrack.autodialer;

import com.cartrack.autodialer.domain.Client;
import com.cartrack.autodialer.domain.ClientList;
import com.cartrack.autodialer.matcher.ModelMatcher;

/**
 * Created by vinner on 07.10.2015.
 */
public class TestData {

    public static final ModelMatcher<Client, String> MATCHER = new ModelMatcher<>(Client::toString);

    public static final ClientList CLIENT_LIST1 = new ClientList(1, "vip clients", "vip clients are clients with status of VIP, privileges");
    public static final ClientList CLIENT_LIST2 = new ClientList(2, "debtors", "the clients which have debts");
    public static final ClientList CLIENT_LIST3 = new ClientList(3, "taxi", "the drivers numbers");
    public static final ClientList CLIENT_LIST4 = new ClientList(4, "testList", "for testing");
//    public static final Client CLIENT1 = new Client(1, "Ivan", "Ivan", "+380638925578", "1@urk.net", CLIENT_LIST1);
//    public static final Client CLIENT2 = new Client(2, "Petrov", "Ivan", "+380638925678", "2@urk.net", CLIENT_LIST1);
//    public static final Client CLIENT3 = new Client(7, "Сергеев", "Дмитрий", "+38055325678", "2@mail.ru", CLIENT_LIST1);
//
//    public static final Client CLIENTNEW = new Client(8, "New Client", "lastname", "phoneNew", "emailnew", CLIENT_LIST1);


}
