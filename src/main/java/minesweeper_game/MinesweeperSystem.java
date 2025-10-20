package minesweeper_game;

import minesweeper_game.command.MoveCommand;
import minesweeper_game.entities.Game;
import minesweeper_game.enums.GameStatus;
import minesweeper_game.observer.GameObserver;
import minesweeper_game.strategy.RandomMinePlacementStrategy;

public class MinesweeperSystem {
    private static final MinesweeperSystem instance = new MinesweeperSystem();
    private Game game;
    private MinesweeperSystem() {}
    public static MinesweeperSystem getInstance() {
        return instance;
    }
    public void createNewGame(int rows, int cols, int numMines) {
        this.game = new Game.Builder()
                .withDimension(rows, cols)
                .withMines(numMines)
                .withMinePlacementStrategy(new RandomMinePlacementStrategy())
                .build();
        System.out.println("New game created (" + rows + "x" + cols + ", " + numMines + " mines).");
    }
    public void addObserver(GameObserver observer) {
        this.game.addObserver(observer);
    }
    public void processMove(MoveCommand command) {
        if (game != null && game.getStatus() != GameStatus.LOST && game.getStatus() != GameStatus.WON) {
            command.execute();
        } else {
            System.out.println("Cannot process move. Game is over or not started.");
        }
    }
    public Game getGame() {
        return game;
    }

    public GameStatus getGameStatus() {
        return (game != null) ? game.getStatus() : null;
    }
}
