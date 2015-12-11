package com.cartrack.autodialer.service;

import com.cartrack.autodialer.ClientTestData;
import com.cartrack.autodialer.domain.CallResult;
import com.cartrack.autodialer.matcher.ModelMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by vinner on 09.12.2015.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-asterisk.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class CallResultServiceTest {

    public static final ModelMatcher<CallResult, String> MATCHER = new ModelMatcher<>(CallResult::toString);

    @Autowired
    protected CallResultService service;

    @Test
    public void testGetByTaskId() throws Exception {
        List<CallResult> callResults = service.getByTaskId(2);
        MATCHER.assertCollectionEquals(Arrays.asList(ClientTestData.CALL_RESULT5, ClientTestData.CALL_RESULT6), callResults);
    }

    @Test
    public void testGetAll() throws Exception {
        List<CallResult> callResults = service.getAll();
        MATCHER.assertCollectionEquals(Arrays.asList(ClientTestData.CALL_RESULT1, ClientTestData.CALL_RESULT2, ClientTestData.CALL_RESULT3, ClientTestData.CALL_RESULT4,
                ClientTestData.CALL_RESULT5, ClientTestData.CALL_RESULT6 ), callResults);
    }

    @Test
    public void testGetBetween() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(ClientTestData.CALL_RESULT6, ClientTestData.CALL_RESULT5, ClientTestData.CALL_RESULT4,
                ClientTestData.CALL_RESULT3, ClientTestData.CALL_RESULT2), service.getBetweenDates(LocalDate.of(2015, Month.DECEMBER, 1), LocalDate.of(2015, Month.DECEMBER, 4)));

    }

    @Test
    public void testSave() throws Exception {
        CallResult callResult = new CallResult(null, LocalDateTime.of(2015, Month.DECEMBER, 4, 18, 0), "success", "success",ClientTestData.TASK2, ClientTestData.CLIENT3);
        CallResult created = service.save(callResult);
        MATCHER.assertCollectionEquals(Arrays.asList(ClientTestData.CALL_RESULT1, ClientTestData.CALL_RESULT2, ClientTestData.CALL_RESULT3,
                ClientTestData.CALL_RESULT4, ClientTestData.CALL_RESULT5, ClientTestData.CALL_RESULT6, ClientTestData.CALL_RESULTNew ), service.getAll());
    }

    @Test
    public void testUpdate() throws Exception {
        CallResult callResultUpdated = new CallResult(ClientTestData.CALL_RESULT1.getId(), LocalDateTime.of(2015, Month.DECEMBER, 4, 18, 0), "success", "success",ClientTestData.TASK2, ClientTestData.CLIENT3);
        service.update(callResultUpdated);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(1);
        MATCHER.assertCollectionEquals(Arrays.asList(ClientTestData.CALL_RESULT2, ClientTestData.CALL_RESULT3, ClientTestData.CALL_RESULT4,
                ClientTestData.CALL_RESULT5, ClientTestData.CALL_RESULT6 ), service.getAll());
    }
}