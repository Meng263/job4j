package ru.job4j.coffeemachine;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CoffeeMachineTest {

    @Test
    public void whenCoffee35Value50thanChangeIs15() {
        CoffeeMachine coffee = new CoffeeMachine();
        int[] result = new int[]{10, 5};
        assertThat(result, is(coffee.changes(50, 35)));
    }

    @Test
    public void whenCoffee20Value100thanChangeIs80() {
        CoffeeMachine coffee = new CoffeeMachine();
        int[] result = new int[]{10, 10, 10, 10, 10, 10, 10, 10};
        assertThat(result, is(coffee.changes(100, 20)));
    }

    @Test
    public void whenCoffee36Value50thanChangeIs14() {
        CoffeeMachine coffee = new CoffeeMachine();
        int[] result = new int[]{10, 2, 2};
        assertThat(result, is(coffee.changes(50, 36)));
    }
}
