package org.asaph.connect4;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

import lombok.Getter;

public class Board {
    public final static int DEFAULT_ROWS = 6;
    public final static int DEFAULT_COLUMNS = 7;
    public final static int DEFAULT_CONNECT_THIS_MANY_TO_WIN = 4;
    @Getter
    private int rows;
    @Getter
    private int columns;
    @Getter
    private int connectThisManyToWin;
    private DiscColor[][] moves;
    private int[] rowHeights;

    public Board(int rows, int columns, int connectThisManyToWin) {
        if (connectThisManyToWin > Math.max(rows, columns)) {
            throw new IllegalArgumentException("connectThisManyToWin value is too big for this board");
        }
        this.rows = rows;
        this.columns = columns;
        this.connectThisManyToWin = connectThisManyToWin;
        this.moves = new DiscColor[this.rows][this.columns];
        this.rowHeights = new int[this.columns];
    }

    public Board() {
        this(DEFAULT_ROWS, DEFAULT_COLUMNS, DEFAULT_CONNECT_THIS_MANY_TO_WIN);
    }

    public void move(int column, DiscColor discColor) {
        if (column < 0 || column >= this.columns) {
            throw new IllegalArgumentException("Invalid column index: " + column);
        }
        if (rowHeights[column] >= this.rows) {
            throw new IllegalArgumentException("Column " + column + " is full");
        }
        moves[rowHeights[column]++][column] = discColor;
    }

    public DiscColor getWinner() {
        for (int r=0; r<rows; r++) {
            for (int c=0; c<columns; c++) {
                DiscColor discColor = this.moves[r][c];
                if (discColor == null) {
                    continue;
                }
                if (c+connectThisManyToWin<=columns) {
                    // check horizontal win
                    boolean win = true;
                    for (int i=c+1; i<c+connectThisManyToWin; i++) {
                        DiscColor nextDiscColor = this.moves[r][i];
                        if (!discColor.equals(nextDiscColor)) {
                            win = false;
                            break;
                        }
                    }
                    if (win) {
                        return discColor;
                    }
                    if (r+connectThisManyToWin<=rows) {
                        // check diagonal going up and right
                        win = true;
                        // check diagonal going up and left
                        for (int i=c+1, j=r+1; j<r+connectThisManyToWin; i++, j++) {
                            DiscColor nextDiscColor = this.moves[j][i];
                            if (!discColor.equals(nextDiscColor)) {
                                win = false;
                                break;
                            }
                        }
                        if (win) {
                            return discColor;
                        }
                    }
                }
                if (r+connectThisManyToWin<=rows) {
                    // check vertical win
                    boolean win = true;
                    for (int j=r+1; j<r+connectThisManyToWin; j++) {
                        DiscColor nextDiscColor = this.moves[j][c];
                        if (!discColor.equals(nextDiscColor)) {
                            win = false;
                            break;
                        }
                    }
                    if (win) {
                        return discColor;
                    }
                    if (connectThisManyToWin-1<=c) {
                        win = true;
                        // check diagonal going up and left
                        for (int i=c-1, j=r+1; j<r+connectThisManyToWin; i--, j++) {
                            DiscColor nextDiscColor = this.moves[j][i];
                            if (!discColor.equals(nextDiscColor)) {
                                win = false;
                                break;
                            }
                        }
                        if (win) {
                            return discColor;
                        }
                    }
                }
            }
        }
        return null;
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
