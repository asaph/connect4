package org.asaph.connect4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    void testBoard() {
        Board board = new Board();
        assertEquals(Board.DEFAULT_ROWS, board.getRows());
        assertEquals(Board.DEFAULT_COLUMNS, board.getColumns());

        board.move(3, DiscColor.RED);
        board.move(3, DiscColor.BLACK);
        board.move(2, DiscColor.RED);
        board.move(3, DiscColor.RED);
        board.move(3, DiscColor.BLACK);

        System.out.println(board.toColorString());
    }
}
