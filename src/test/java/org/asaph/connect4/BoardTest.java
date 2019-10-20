package org.asaph.connect4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    void testBoard() {
        Board board = new Board();
        assertEquals(Board.DEFAULT_ROWS, board.getRows());
        assertEquals(Board.DEFAULT_COLUMNS, board.getColumns());
    }
}
