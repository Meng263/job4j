package ru.job4j.singleton;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrackerSingleTest {

    @Test
    public void whenTrackerEnumIsSingleton() {
        TrackerEnum one = TrackerEnum.INSTANCE;
        TrackerEnum two = TrackerEnum.INSTANCE;
        assertThat(one, is(two));
        assertThat(one.equals(two), is(true));
        assertThat(one.hashCode() == two.hashCode(), is(true));
    }

    @Test
    public void whenTrackerStaticFieldIsSingleton() {
       TrackerStaticField one = TrackerStaticField.getInstance();
        TrackerStaticField two = TrackerStaticField.getInstance();
        assertThat(one, is(two));
        assertThat(one.equals(two), is(true));
        assertThat(one.hashCode() == two.hashCode(), is(true));
    }

    @Test
    public void whenTrackerStaticFinalFieldIsSingleton() {
        TrackerStaticFinalField one = TrackerStaticFinalField.getInstance();
        TrackerStaticFinalField two = TrackerStaticFinalField.getInstance();
        assertThat(one, is(two));
        assertThat(one.equals(two), is(true));
        assertThat(one.hashCode() == two.hashCode(), is(true));
    }

    @Test
    public void whenTrackerStaticFinalClassIsSingleton() {
        TrackerStaticFinalClass one = TrackerStaticFinalClass.getInstance();
        TrackerStaticFinalClass two = TrackerStaticFinalClass.getInstance();
        assertThat(one, is(two));
        assertThat(one.equals(two), is(true));
        assertThat(one.hashCode() == two.hashCode(), is(true));
    }
}