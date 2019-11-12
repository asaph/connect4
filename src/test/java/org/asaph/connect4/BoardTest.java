package org.asaph.connect4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    void testBoard() {
        Board board = new Board();
        assertEquals(Board.DEFAULT_ROWS, board.getRows());
        assertEquals(Board.DEFAULT_COLUMNS, board.getColumns());
        assertEquals(Board.DEFAULT_CONNECT_THIS_MANY_TO_WIN, board.getConnectThisManyToWin());

        board.move(3, DiscColor.RED);
        board.move(3, DiscColor.BLACK);
        board.move(2, DiscColor.RED);
        board.move(3, DiscColor.RED);
        board.move(3, DiscColor.BLACK);

        System.out.println(board.toColorString());
    }

    @Test
    void testInvalidMoveNegativeIndex() {
        Board board = new Board();
        assertThrows(IllegalArgumentException.class, () -> board.move(-1, DiscColor.RED));
    }

    @Test
    void testInvalidMoveIndexOutOfBounds() {
        Board board = new Board();
        assertThrows(IllegalArgumentException.class, () -> board.move(Board.DEFAULT_COLUMNS, DiscColor.RED));
    }

    @Test
    void testGetWinnerEmptyBoard() {
        Board board = new Board();
        assertNull(board.getWinner());
    }

    @Test
    void testRedWinsVertically() {
        Board board = new Board();
        board.move(3, DiscColor.RED);
        board.move(2, DiscColor.BLACK);
        board.move(3, DiscColor.RED);
        board.move(2, DiscColor.BLACK);
        board.move(3, DiscColor.RED);
        board.move(2, DiscColor.BLACK);
        assertNull(board.getWinner());
        board.move(3, DiscColor.RED);
        assertEquals(DiscColor.RED, board.getWinner());
    }

    @Test
    void testRedWinsHorizontally() {
        Board board = new Board();
        board.move(3, DiscColor.RED);
        board.move(2, DiscColor.BLACK);
        board.move(4, DiscColor.RED);
        board.move(2, DiscColor.BLACK);
        board.move(5, DiscColor.RED);
        board.move(2, DiscColor.BLACK);
        assertNull(board.getWinner());
        board.move(6, DiscColor.RED);
        assertEquals(DiscColor.RED, board.getWinner());
    }


    @Test
    void testRedWinsDiagonallyUpAndRight() {
        Board board = new Board();
        board.move(3, DiscColor.RED);
        board.move(4, DiscColor.BLACK);
        board.move(4, DiscColor.RED);
        board.move(5, DiscColor.BLACK);
        board.move(5, DiscColor.RED);
        board.move(6, DiscColor.BLACK);
        board.move(5, DiscColor.RED);
        board.move(6, DiscColor.BLACK);
        board.move(6, DiscColor.RED);
        board.move(3, DiscColor.BLACK);
        assertNull(board.getWinner());
        board.move(6, DiscColor.RED);
        assertEquals(DiscColor.RED, board.getWinner());
    }

    @Test
    void testRedWinsDiagonallyUpAndLeft() {
        Board board = new Board();
        board.move(3, DiscColor.RED);
        board.move(2, DiscColor.BLACK);
        board.move(2, DiscColor.RED);
        board.move(1, DiscColor.BLACK);
        board.move(1, DiscColor.RED);
        board.move(0, DiscColor.BLACK);
        board.move(1, DiscColor.RED);
        board.move(0, DiscColor.BLACK);
        board.move(0, DiscColor.RED);
        board.move(3, DiscColor.BLACK);
        assertNull(board.getWinner());
        board.move(0, DiscColor.RED);
        assertEquals(DiscColor.RED, board.getWinner());
    }
}
