package ru.job4j.sql.sqllite;

import org.junit.After;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StoreSQLTest {
    StoreSQL storeSQL = new StoreSQL(new Config());

    @After
    public void tearDown() throws Exception {
        storeSQL.dropAll();
    }

    @Test
    public void generate() {
        storeSQL.generate(100);
        List<Entry> list = storeSQL.load();
        assertThat(list.size(), is(100));
    }

}