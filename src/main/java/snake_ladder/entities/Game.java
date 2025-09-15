package snake_ladder.entities;

import snake_ladder.enums.GameStatus;
import snake_ladder.observer.GameSubject;
import snake_ladder.state.GameState;
import snake_ladder.state.InProgressState;

public class Game extends GameSubject {
    private final Player player1;
    private final Player player2;

    private final Board board;

    private GameState state;
    private GameStatus status;
    private Player winner;
    private Player currentPlayer;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Board(10);
        this.currentPlayer = player1;
        this.status = GameStatus.IN_PROGRESS;
        this.state = new InProgressState();
    }
    public void addSnakes(Snake[] snakes) {
        this.board.addSnakes(snakes);
    }
    public void addLadders(Ladder[] ladders) {
        this.board.addLadders(ladders);
    }

    public void makeMove(Player player, int row, int col) {
        this.state.handleMove(this, player, row, col);
    }
    public Player getCurentPlayer() {
        return currentPlayer;
    }
    public Board getBoard() {
        return board;
    }

    public boolean checkWinner(Player player) {
        return player.getPosition()[0] == 9 && player.getPosition()[1] == 9;
    }

    public void setWinner(Player player) {
        this.winner = player;
    }

    public void setStatus(GameStatus gameStatus) {
        this.status = gameStatus;
        if(status != GameStatus.IN_PROGRESS) {
            notifyObservers();
        }
    }

    public void setState(GameState gameState) {
        this.state = gameState;
    }

    public Player getWinner() {
        return winner;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1)?player2:player1;
    }

    public GameStatus getStatus() {
        return status;
    }
}
