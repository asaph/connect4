package org.asaph.connect4;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

import lombok.Getter;

public class Board {
    public final static int DEFAULT_ROWS = 6;
    public final static int DEFAULT_COLUMNS = 7;
    @Getter
    private int rows;
    @Getter
    private int columns;
    private DiscColor[][] moves;
    private int[] rowHeights;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.moves = new DiscColor[this.rows][this.columns];
        this.rowHeights = new int[this.rows];
    }

    public Board() {
        this(DEFAULT_ROWS, DEFAULT_COLUMNS);
    }

    @Override
    public String toString() {
        return toString(false);
    }

    public String toColorString() {
        return toString(true);
    }

    public String toString(boolean ansiColor) {
        StringBuffer sb = new StringBuffer();
        if (ansiColor) {
            sb.append(ansi().fg(YELLOW));
        }
        for (int r=0; r<rows; r++) {
            for (int c=0; c<columns; c++) {
                sb.append("+---");
            }
            sb.append("+\n");
            for (int c=0; c<columns; c++) {
                sb.append("| ");
                DiscColor move = moves[rows - 1 - r][c];
                if (DiscColor.RED.equals(move)) {
                    if (ansiColor) {
                        sb.append(ansi().fg(RED).a('O').fg(YELLOW));
                    } else {
                        sb.append('R');
                    }
                } else if (DiscColor.BLACK.equals(move)) {
                    if (ansiColor) {
                        sb.append(ansi().fg(GREEN).a('O').fg(YELLOW));
                    } else {
                        sb.append('B');
                    }
                } else {
                    sb.append(' ');
                }
                sb.append(' ');
            }
            sb.append("|\n");
        }
        for (int c=0; c<columns; c++) {
            sb.append("+---");
        }
        sb.append('+');
        if (ansiColor) {
            sb.append(ansi().reset());
        }
        sb.append('\n');
        return sb.toString();
    }
}
