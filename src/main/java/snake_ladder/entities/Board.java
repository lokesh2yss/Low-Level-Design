package snake_ladder.entities;

import snake_ladder.enums.Symbol;

public class Board {
    private int size;
    private Cell[][] board;
    private Snake[] snakes;
    private Ladder[] ladders;

    public Board(int size) {
        this.size = size;
        initializeBoard();
    }
    public void initializeBoard() {
        board = new Cell[size][size];
        for(int row=0; row<size; row++) {
            for(int col=0; col < size; col++) {
                board[row][col] = new Cell();
            }
        }
    }
    public void addSnakes(Snake[] snakes) {
        this.snakes = snakes;
        for(Snake snake: snakes) {
            int[] start = snake.getStart();
            board[start[0]][start[1]] = new Cell(Symbol.ST);
            int[] end = snake.getEnd();
            board[end[0]][end[1]] = new Cell(Symbol.SH);
        }
    }

    public void addLadders(Ladder[] ladders) {
        this.ladders = ladders;
        for(Ladder ladder: ladders) {
            int[] start = ladder.getStart();
            board[start[0]][start[1]] = new Cell(Symbol.LT);
            int[] end = ladder.getEnd();
            board[end[0]][end[1]] = new Cell(Symbol.LH);
        }
    }
    public int[] getSnakeTail(int headX, int headY) {
        for(Snake snake: snakes) {
            if(snake.getEnd()[0] == headX && snake.getEnd()[1] == headY) {
                return snake.getStart();
            }
        }
        return new int[] {0, 0};
    }
    public int[] getLadderHead(int tailX, int tailY) {
        for(Ladder ladder: ladders) {
            if(ladder.getStart()[0] == tailX && ladder.getStart()[1] == tailY) {
                return ladder.getEnd();
            }
        }
        return new int[] {0, 0};
    }
    public Cell getCell(int row, int col) {
        if(row < 0 || row >= size || col < 0 || col >= size) {
            return null;
        }
        return board[row][col];
    }
    public void printBoard() {
        System.out.println("-------------");
        for (int i = size-1; i >=0; i--) {
            System.out.print("| ");
            for (int j = size-1; j >=0; j--) {
                Symbol symbol = board[i][j].getSymbol();
                System.out.print(symbol.getChar() + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    public int getSize() {
        return size;
    }

}
