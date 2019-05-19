package ru.job4j.singleton;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TrackerSingleTest {

    @Test
    public void whenTrackerEnumIsSingleton() {
        TrackerEnum one = TrackerEnum.INSTANCE;
        TrackerEnum two = TrackerEnum.INSTANCE;
        assertTrue(one == two);
    }

    @Test
    public void whenTrackerStaticFieldIsSingleton() {
       TrackerStaticField one = TrackerStaticField.getInstance();
        TrackerStaticField two = TrackerStaticField.getInstance();
        assertTrue(one == two);
    }

    @Test
    public void whenTrackerStaticFinalFieldIsSingleton() {
        TrackerStaticFinalField one = TrackerStaticFinalField.getInstance();
        TrackerStaticFinalField two = TrackerStaticFinalField.getInstance();
        assertTrue(one == two);
    }

    @Test
    public void whenTrackerStaticFinalClassIsSingleton() {
        TrackerStaticFinalClass one = TrackerStaticFinalClass.getInstance();
        TrackerStaticFinalClass two = TrackerStaticFinalClass.getInstance();
        assertTrue(one == two);
    }
}