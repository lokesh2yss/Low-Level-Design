package snake_ladder;

import snake_ladder.entities.*;
import tictactoe.exceptions.InvalidMoveException;

import java.util.Arrays;

public class SnakeLadderSystem {
    private static volatile SnakeLadderSystem instance;
    private Game game;
    private final Scoreboard scoreboard;

    private SnakeLadderSystem() {
        this.scoreboard = new Scoreboard();
    }
    public static synchronized SnakeLadderSystem getInstance() {
        if(instance == null) {
            instance = new SnakeLadderSystem();
        }
        return instance;
    }
    public void addSnakes(Snake[] snakes) {
        this.game.addSnakes(snakes);
    }
    public void addLadders(Ladder[] ladders) {
        this.game.addLadders(ladders);
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }
    public void createGame(Player player1, Player player2) {
        this.game = new Game(player1, player2);
        this.game.addObserver(scoreboard);
        System.out.printf("Game started between %s (X) and %s (O).%n", player1.getName(), player2.getName());
    }
    public void makeMove(Player player, int row, int col) {
        if (game == null) {
            System.out.println("No game in progress. Please create a game first.");
            return;
        }
        try {
            System.out.printf("%s plays at (%d, %d)%n", player.getName(), row, col);
            game.makeMove(player, row, col);
            System.out.println("Player position is:");
            System.out.println(Arrays.toString(player.getPosition()));
            //printBoard();
            System.out.println("Game Status: " + game.getStatus());
            if (game.getWinner() != null) {
                System.out.println("Winner: " + game.getWinner().getName());
            }
        } catch (InvalidMoveException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void printBoard() {
        this.game.getBoard().printBoard();
    }

    public void printScoreBoard() {
        scoreboard.printScores();
    }
}

