package com.cartrack.autodialer.service;

import com.cartrack.autodialer.ClientTestData;
import com.cartrack.autodialer.domain.OriginateParam;
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
 * Created by vinner on 25.11.2015.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class OriginateParamServiceTest {

    public static final ModelMatcher<OriginateParam, String> MATCHER = new ModelMatcher<>(OriginateParam::toString);

    @Autowired
    protected OriginateParamService service;

    @Test
    public void testGetAll() throws Exception {
        List<OriginateParam> originateParamList = service.getAll();
        MATCHER.assertCollectionEquals(Arrays.asList(ClientTestData.ORIGINATE_PARAM1, ClientTestData.ORIGINATE_PARAM2), originateParamList);
    }

    @Test
    public void testGet() throws Exception {
        OriginateParam originateParam = service.get(1);
        MATCHER.assertEquals(ClientTestData.ORIGINATE_PARAM1, originateParam);
    }

    @Test
    public void testSave() throws Exception {
        OriginateParam originateParamNew = new OriginateParam(null, "from-ami-new", "from-ami-new", "s", 1, false, 30000, "var1ValueNew", "var2ValueNew");
        OriginateParam created = service.save(originateParamNew);

        MATCHER.assertCollectionEquals(Arrays.asList(ClientTestData.ORIGINATE_PARAM1, ClientTestData.ORIGINATE_PARAM2, ClientTestData.ORIGINATE_PARAMNEW), service.getAll());
    }

    @Test
    public void testUpdate() throws Exception {
        OriginateParam updated = new OriginateParam(ClientTestData.ORIGINATE_PARAM1.getId(), "UPDATED", "UPDATED", "s", 1, false, 30000, "var1ValueNew", "var2ValueNew");
        service.update(updated);
        MATCHER.assertEquals(updated, service.get(1));
    }

    @Test
    public void testDelete() throws Exception {
//        service.delete(1);
//        MATCHER.assertCollectionEquals(Arrays.asList(ClientTestData.ORIGINATE_PARAM2), service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(10000);
    }


}