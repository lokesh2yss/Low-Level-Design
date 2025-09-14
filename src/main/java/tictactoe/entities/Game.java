package tictactoe.entities;

import tictactoe.enums.GameStatus;
import tictactoe.observer.GameSubject;
import tictactoe.state.GameState;
import tictactoe.state.InProgressState;
import tictactoe.strategy.ColumnWinningStrategy;
import tictactoe.strategy.DiagonalWinningStrategy;
import tictactoe.strategy.RowWinningStrategy;
import tictactoe.strategy.WinningStrategy;

import java.util.List;

public class Game extends GameSubject {
    private final Board board;

    private final Player player1;
    private final Player player2;
    private GameState state;
    private Player winner;
    private Player currentPlayer;

    private List<WinningStrategy> winningStrategies;
    private GameStatus status;

    public Game(Player player1, Player player2) {
        this.board = new Board(3);
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.state = new InProgressState();
        this.status = GameStatus.IN_PROGRESS;
        this.winningStrategies = List.of(
                new RowWinningStrategy(),
                new ColumnWinningStrategy(),
                new DiagonalWinningStrategy()
        );
    }
    public void makeMove(Player player, int row, int col) {
        this.state.handleMove(this, player, row, col);
    }

    public boolean checkWinner(Player player) {
        for(WinningStrategy strategy: this.winningStrategies) {
            if(strategy.checkWinner(this.board, player)) {
                return true;
            }
        }
        return false;
    }
    public void switchPlayer() {
        this.currentPlayer = currentPlayer == player1?player2:player1;
    }

    public Board getBoard() {
        return board;
    }

    public Player getWinner() {
        return winner;
    }
    public Player getCurrentPlayer() {
        return currentPlayer;
    }


    public void setWinner(Player player) {
        this.winner = player;
    }

    public GameStatus getStatus() {
        return status;
    }
    public void setStatus(GameStatus gameStatus) {
        this.status = gameStatus;
        // Notify observers when the status changes to a finished state
        if (status != GameStatus.IN_PROGRESS) {
            notifyObservers();
        }
    }

    public void setState(GameState winnerState) {
        this.state = winnerState;
    }


}
