package ru.job4j.generic;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class StoreTest {
    @Test
    public void whenStoreUserThenResult() {
        UserStore userStore = new UserStore(new SimpleArray<User>(10));
        userStore.add(new User("1"));
        userStore.add(new User("2"));
        User searchUser = userStore.findById("2");
        assertThat(userStore.getValues().get(1), is(searchUser));
        userStore.delete("1");
        assertThat(userStore.getValues().get(0), is(searchUser));
        userStore.replace("2", new User("3"));
        assertThat(userStore.getValues().get(0).getId(), is("3"));
    }

    @Test
    public void whenStoreRoleThenResult() {
        RoleStore roleStore = new RoleStore(new SimpleArray<Role>(10));
        roleStore.add(new Role("1"));
        roleStore.add(new Role("2"));
        Role searchRole = roleStore.findById("2");
        assertThat(roleStore.getValues().get(1), is(searchRole));
        roleStore.delete("1");
        assertThat(roleStore.getValues().get(0), is(searchRole));
        Role replaceRole = new Role("3");
        roleStore.replace("2", replaceRole);
        assertThat(roleStore.getValues().get(0).getId(), is("3"));
    }
}