package com.cartrack.autodialer;

import com.cartrack.autodialer.domain.Client;
import com.cartrack.autodialer.domain.ClientList;
import com.cartrack.autodialer.domain.OriginateParam;
import com.cartrack.autodialer.domain.Task;
import com.cartrack.autodialer.matcher.ModelMatcher;

/**
 * Created by vinner on 07.10.2015.
 */
public class ClientTestData {

    public static final ModelMatcher<Client, String> MATCHER = new ModelMatcher<>(Client::toString);

    public static final ClientList CLIENT_LIST1 = new ClientList(1, "vip clients", "vip clients are clients with status of VIP, privileges");
    public static final ClientList CLIENT_LIST2 = new ClientList(2, "debtors", "the clients which have debts");
    public static final ClientList CLIENT_LIST3 = new ClientList(3, "taxi", "the drivers numbers");


    public static final Client CLIENT1 = new Client(1, "Ivan", "Ivan", "+380638925578", "1@urk.net", CLIENT_LIST1);
    public static final Client CLIENT2 = new Client(2, "Petrov", "Ivan", "+380638925678", "2@urk.net", CLIENT_LIST1);
    public static final Client CLIENT3 = new Client(7, "Сергеев", "Дмитрий", "+38055325678", "2@mail.ru", CLIENT_LIST1);
    public static final Client CLIENT4 = new Client(8, "Наумова", "Дмитрий", "+380738625020", "naumov@mail.ru", CLIENT_LIST1);

    public static final Client CLIENTNEW = new Client(9, "New Client", "lastname", "phoneNew", "emailnew", CLIENT_LIST1);

    public static final OriginateParam ORIGINATE_PARAM1 = new OriginateParam(1, "from-ami", "from-ami", "s", 1, false, 30000, "var1Value", "var2Value");
    public static final OriginateParam ORIGINATE_PARAM2 = new OriginateParam(2, "outbount without handlers", "from-admin", "s", 1, false, 20000, "test", "test");

    public static final OriginateParam ORIGINATE_PARAMNEW = new OriginateParam(3, "from-ami-new", "from-ami-new", "s", 1, false, 30000, "var1ValueNew", "var2ValueNew");

    public static final Task TASK1 = new Task(1, "vip", CLIENT_LIST1, true, ORIGINATE_PARAM1);
    public static final Task TASK2 = new Task(2, "debtors dialer", CLIENT_LIST2, true, ORIGINATE_PARAM1);
    public static final Task TASK3 = new Task(3, "new clients", CLIENT_LIST3, true, ORIGINATE_PARAM2);
    public static final Task TASK4 = new Task(4, "hold calls", CLIENT_LIST2, true, ORIGINATE_PARAM1);
    public static final Task TASK5 = new Task(5, "безнадежные звонки", CLIENT_LIST2, true, ORIGINATE_PARAM2);
    public static final Task TASKNEW = new Task(6, "newTask", CLIENT_LIST1, true, ORIGINATE_PARAM2);





}
