package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.white.BishopWhite;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BishopWhiteTest {
    @Test
    public void WhenBishopTurnPlusXPlusY() {
        BishopWhite bishop = new BishopWhite(Cell.A1);
        Cell[] result = bishop.way(Cell.A1, Cell.D4);
        assertThat(result, is(new Cell[]{Cell.B2, Cell.C3, Cell.D4}));
    }

    @Test
    public void WhenBishopTurnPlusXPMinusY() {
        BishopWhite bishop = new BishopWhite(Cell.A5);
        Cell[] result = bishop.way(Cell.A5, Cell.E1);
        assertThat(result, is(new Cell[]{Cell.B4, Cell.C3, Cell.D2, Cell.E1}));
    }

    @Test
    public void WhenBishopTurnMinusXPlusY() {
        BishopWhite bishop = new BishopWhite(Cell.G1);
        Cell[] result = bishop.way(Cell.G1, Cell.D4);
        assertThat(result, is(new Cell[]{Cell.F2, Cell.E3, Cell.D4}));
    }

}
