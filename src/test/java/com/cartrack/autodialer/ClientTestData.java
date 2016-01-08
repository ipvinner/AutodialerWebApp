package com.cartrack.autodialer;

import com.cartrack.autodialer.domain.*;
import com.cartrack.autodialer.matcher.ModelMatcher;
import com.cartrack.autodialer.util.TimeUtil;

import java.time.LocalDateTime;
import java.time.Month;

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
    public static final Client CLIENT3 = new Client(3, "Petrov", "Test", "+380688325678", "3@urk.net", CLIENT_LIST2);
    public static final Client CLIENT4 = new Client(4, "Семенова", "Анна", "+38098325678", "4@urk.net", CLIENT_LIST2);
    public static final Client CLIENT5 = new Client(5, "Феофанов", "Дмитрий", "+38050325678", "5@urk.net", CLIENT_LIST2);
    public static final Client CLIENT6 = new Client(6, "Феофанов", "Андрей", "+38050325678", "6@urk.net", CLIENT_LIST3);
    public static final Client CLIENT7 = new Client(7, "Сергеев", "Дмитрий", "+38055325678", "2@mail.ru", CLIENT_LIST1);
    public static final Client CLIENT8 = new Client(8, "Наумова", "Дмитрий", "+380738625020", "naumov@mail.ru", CLIENT_LIST1);

    public static final Client CLIENTNEW = new Client(9, "New Client", "lastname", "phoneNew", "emailnew", CLIENT_LIST1);

    public static final OriginateParam ORIGINATE_PARAM1 = new OriginateParam(1, "from-ami", "from-ami", "s", 1, false, 30000, "var1Value", "var2Value", "SIP/zadarma/");
    public static final OriginateParam ORIGINATE_PARAM2 = new OriginateParam(2, "outbount without handlers", "from-admin", "s", 1, false, 20000, "test", "test", "SIP/zadarma/");

    public static final OriginateParam ORIGINATE_PARAMNEW = new OriginateParam(3, "from-ami-new", "from-ami-new", "s", 1, false, 30000, "var1ValueNew", "var2ValueNew", "SIP/zadarma/");

    public static final Task TASK1 = new Task(1, "vip", CLIENT_LIST1, true, ORIGINATE_PARAM1);
    public static final Task TASK2 = new Task(2, "debtors dialer", CLIENT_LIST2, true, ORIGINATE_PARAM1);
    public static final Task TASK3 = new Task(3, "new clients", CLIENT_LIST3, true, ORIGINATE_PARAM2);
    public static final Task TASK4 = new Task(4, "hold calls", CLIENT_LIST2, true, ORIGINATE_PARAM1);
    public static final Task TASK5 = new Task(5, "безнадежные звонки", CLIENT_LIST2, true, ORIGINATE_PARAM2);
    public static final Task TASKNEW = new Task(6, "newTask", CLIENT_LIST1, true, ORIGINATE_PARAM2);


    public static final CallResult CALL_RESULT1 = new CallResult(1, LocalDateTime.of(2015, Month.NOVEMBER, 30, 10, 0), "success", "success", TASK1, CLIENT1);
    public static final CallResult CALL_RESULT2 = new CallResult(2, LocalDateTime.of(2015, Month.DECEMBER, 1, 11, 0), "failed", "no-answer", TASK1, CLIENT2);
    public static final CallResult CALL_RESULT3 = new CallResult(3, LocalDateTime.of(2015, Month.DECEMBER, 1, 12, 0), "failed", "no-answer", TASK1, CLIENT3);
    public static final CallResult CALL_RESULT4 = new CallResult(4, LocalDateTime.of(2015, Month.DECEMBER, 2, 13, 0), "success", "success", TASK1, CLIENT3);
    public static final CallResult CALL_RESULT5 = new CallResult(5, LocalDateTime.of(2015, Month.DECEMBER, 2, 14, 0), "success", "success", TASK2, CLIENT3);
    public static final CallResult CALL_RESULT6 = new CallResult(6, LocalDateTime.of(2015, Month.DECEMBER, 2, 18, 0), "failed", "busy", TASK2, CLIENT3);

    public static final CallResult CALL_RESULTNew = new CallResult(7, LocalDateTime.of(2015, Month.DECEMBER, 4, 18, 0), "success", "success", TASK2, CLIENT3);


    public static final ClientList CLIENT_LIST_NEW = new ClientList(4, "new", "new");
    public static final Client CLIENT9 = new Client(9, "Феофанов", "Андрей", "+38050325678", "6@urk.net", CLIENT_LIST_NEW);
    public static final Client CLIENT10 = new Client(10, "Сергеев", "Дмитрий", "+38055325678", "2@mail.ru", CLIENT_LIST_NEW);
    public static final Client CLIENT11 = new Client(11, "Наумова", "Дмитрий", "+380738625020", "naumov@mail.ru", CLIENT_LIST_NEW);


}
