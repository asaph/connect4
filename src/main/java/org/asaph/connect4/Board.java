package org.asaph.connect4;

import lombok.Getter;

public class Board {
    public final static int DEFAULT_ROWS = 6;
    public final static int DEFAULT_COLUMNS = 7;
    @Getter
    private int rows;
    @Getter
    private int columns;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public Board() {
        this(DEFAULT_ROWS, DEFAULT_COLUMNS);
    }
}
