package com.bmx.verde.instrument;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.bmx.verde.dao.RddbDaoImpl;
import com.bmx.verde.model.InstrumentOpticConfig;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

public class RDDBDaoImplTests {

    public RDDBDaoImplTests() {
    }

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private RddbDaoImpl dao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_loadOpticConfigs() {
        List<InstrumentOpticConfig> list = List.of(new InstrumentOpticConfig());
        when(jdbcTemplate.query(any(String.class), any(ResultSetExtractor.class))).thenReturn(list);
        List<InstrumentOpticConfig> response = dao.loadOpticConfigs();
        assertNotNull(response);
    }

    @Test
    public void test_addDispoTest() {
        when(jdbcTemplate.update(any(String.class))).thenReturn(0);
        List<Object> list = List.of(1);
        when(jdbcTemplate.query(any(String.class), any(ResultSetExtractor.class))).thenReturn(list);

        int response = dao.addDispoTest(1, "sd", 2, 3, "barcode", 5, "defaultStorageConditions", "defaultBadFlag",
                "defaultFlagActive", "2019-08-21 10:16:28", "serialNumber", "instrumentType", "itgStatusCode",
                "dilutionType", "barcodDispoTest");
        assertThat(response, is(0));
    }

    @Test
    public void test_updateDispoTest() {

        when(jdbcTemplate.update(any(String.class))).thenReturn(0);
        int response = dao.updateDispoTest(2, "dilutionType", 3, "instrSerialNumber", "instrType",
                "2019-08-21 10:16:28", "P");
        assertThat(response, is(0));
    }

    @Test
    public void test_addTypDispo() {
        when(jdbcTemplate.update(any(String.class))).thenReturn(0);
        int response = dao.addTypDisp("cardName", 2, "SUSC", "instrumentType", "activeFlag", "productionFlag",
                "barcode");
        assertThat(response, is(0));
    }
}
