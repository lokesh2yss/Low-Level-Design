package minesweeper_game.entities;

import minesweeper_game.enums.GameStatus;
import minesweeper_game.observer.GameObserver;
import minesweeper_game.state.RevealedState;
import minesweeper_game.strategy.MinePlacementStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Board board;
    private GameStatus gameStatus;
    private final int mineCount;
    private final List<GameObserver> observers = new ArrayList<>();

    private Game(Board board, int mineCount) {
        this.board = board;
        this.mineCount = mineCount;
        this.gameStatus = GameStatus.IN_PROGRESS;
    }
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }
    public void notifyObservers() {
        for(GameObserver observer: observers) {
            observer.update(this);
        }
    }
    public void revealCell(int r, int c) {
        if(gameStatus != GameStatus.IN_PROGRESS) return;
        Cell cell = board.getCell(r, c);
        if(cell.isRevealed() || cell.isFlagged()) return;
        cell.reveal();
        if(cell.isMine()) {
            gameStatus = GameStatus.LOST;
        }
        else {
            if(cell.getAdjacentMinesCount() == 0) {
                revealNeighbours(r, c);
            }
            checkWinCondition();
        }
        notifyObservers();
    }
    private void revealNeighbours(int r, int c) {
        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j<= 1; j++) {
                if(i==0 || j==0) {
                    continue;
                }
                int nr = r + i;
                int nc = c + j;
                if(nr >= 0 && nr < board.getRows() && nc >=0 && nc < board.getCols()) {
                    revealCell(nr, nc);
                }
            }
        }
    }
    public void flagCell(int r, int c) {
        if(gameStatus != GameStatus.IN_PROGRESS) return;
        board.getCell(r, c).flag();
        notifyObservers();
    }
    public void unflagCell(int r, int c) {
        if(gameStatus != GameStatus.IN_PROGRESS) return;
        board.getCell(r, c).unflag();
        notifyObservers();
    }
    public void checkWinCondition() {
        int revealedCount = 0;
        for(int r=0; r< board.getRows(); r++) {
            for(int c=0; c< board.getCols(); c++) {
                if(board.getCell(r, c).isRevealed()) {
                    revealedCount++;
                }
            }
        }
        if(revealedCount == (board.getRows()*board.getCols()) - mineCount) {
            gameStatus = GameStatus.WON;
        }
    }
    public char getCellDisplayChar(int r, int c) {
        // For final display when game is over
        if(gameStatus == GameStatus.LOST && board.getCell(r, c).isMine()) {
            return '*';
        }
        return board.getCell(r, c).getDisplayChar();
    }

    public GameStatus getStatus() {
        return gameStatus;
    }

    public int getRows() {
        return board.getRows();
    }
    public int getCols() {
        return board.getCols();
    }

    public Board getBoard() {
        return board;
    }

    public static class Builder {
        private int rows = 10;
        private int cols = 10;
        private int minesCount = 10;
        private MinePlacementStrategy minePlacementStrategy;
        public Builder withDimension(int rows, int cols) {
            this.rows = rows;
            this.cols = cols;
            return this;
        }
        public Builder withMines(int minesCount) {
            this.minesCount = minesCount;
            return this;
        }
        public Builder withMinePlacementStrategy(MinePlacementStrategy strategy) {
            this.minePlacementStrategy = strategy;
            return this;
        }
        public Game build() {
            if(minesCount > rows*cols) {
                throw new IllegalArgumentException("Mine count must be less than the total number of cells.");
            }
            Board board1 = new Board(rows, cols, minesCount, minePlacementStrategy);
            return new Game(board1, minesCount);
        }
    }
}
