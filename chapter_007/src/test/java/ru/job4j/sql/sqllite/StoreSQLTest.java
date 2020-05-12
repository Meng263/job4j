package ru.job4j.sql.sqllite;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StoreSQLTest {

    @Test
    public void whenPutNEntriesThanGetNEntries() {
        final int rows = 1000;
        try (StoreSQL storeSQL = new StoreSQL(new Config())) {
            storeSQL.generate(rows);
            List<Entry> list = storeSQL.load();
            assertThat(list.size(), is(rows));
            storeSQL.dropTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenPutAndCalculateSumThanEqualsSum() {
        try (StoreSQL storeSQL = new StoreSQL(new Config())) {
            final int rows = 1000;
            storeSQL.generate(rows);
            List<Entry> entries = storeSQL.load();
            UtilXML utilXML = new UtilXML();
            utilXML.listToXml(entries);
            utilXML.xmlToXmlByXstl();
            int sum = utilXML.parseXmlAndEvaluateSum();
            int expected = sum(rows);
            assertThat(expected, is(sum));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int sum(int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += i;
        }
        return sum;
    }
}