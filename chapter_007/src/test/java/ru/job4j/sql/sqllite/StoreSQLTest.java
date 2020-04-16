package ru.job4j.sql.sqllite;

import org.junit.After;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StoreSQLTest {
    StoreSQL storeSQL = new StoreSQL(new Config());

    @After
    public void dropTable() {
        storeSQL.dropTable();
    }

    @Test
    public void whenPutNEntriesThanGetNEntries() {
        final int rows = 1000;
        storeSQL.generate(rows);
        List<Entry> list = storeSQL.load();
        assertThat(list.size(), is(rows));
    }

    @Test
    public void whenPutAndCalculateSumThanEqualsSum() throws JAXBException, TransformerException {
        final int rows = 1000000;
        storeSQL.generate(rows);
        storeSQL.load();
        storeSQL.listToXml();
        storeSQL.xmlToXmlByXstl();
        int sum = storeSQL.parseXmlAndEvaluateSum();
        int expected = sum(rows);
        assertThat(expected, is(sum));
    }

    private int sum(int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += i;
        }
        return sum;
    }
}