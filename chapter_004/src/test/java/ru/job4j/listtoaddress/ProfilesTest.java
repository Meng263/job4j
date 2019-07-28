package ru.job4j.listtoaddress;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProfilesTest {
    @Test
    public void whenListProfileToListAddress() {
        Address ekt = new Address("Yekaterinburg", "Lenina", 5, 55);
        Address london = new Address("London", "Baiker", 3, 5);
        Address paris = new Address("Paris", "Elesey", 13, 1);
        Address moscow = new Address("Moscow", "Kutuzovskiy", 22, 58);
        Profile jora = new Profile(ekt);
        Profile lena = new Profile(london);
        Profile tanja = new Profile(paris);
        Profile lina = new Profile(moscow);
        List<Profile> profiles = List.of(
                jora,
                lena,
                tanja,
                lina
        );
        List<Address> expected = List.of(
                ekt,
                london,
                paris,
                moscow
        );
        Profiles prof = new Profiles();
        assertThat(expected, is(prof.collect(profiles)));
    }

    @Test
    public void whenListProfileToListAddressSorted() {
        Address ekt = new Address("Yekaterinburg", "Lenina", 5, 55);
        Address london = new Address("London", "Baiker", 3, 5);
        Address parisOne = new Address("Paris", "Elesey", 21, 11);
        Address parisTwo = new Address("Paris", "Elesey", 13, 1);
        Address moscow = new Address("Moscow", "Kutuzovskiy", 22, 58);
        Profile jora = new Profile(ekt);
        Profile linaOne = new Profile(moscow);
        Profile lena = new Profile(london);
        Profile tanja = new Profile(parisOne);
        Profile gena = new Profile(parisTwo);
        Profile oleg = new Profile(parisOne);
        Profile linaTwo = new Profile(moscow);

        List<Profile> profiles = List.of(
                jora,
                linaOne,
                lena,
                tanja,
                gena,
                oleg,
                linaTwo
        );
        List<Address> expected = List.of(
                london,
                moscow,
                parisOne,
                parisTwo,
                ekt
        );
        Profiles prof = new Profiles();
        assertThat(expected, is(prof.collectUniqueSorted(profiles)));
    }
}
