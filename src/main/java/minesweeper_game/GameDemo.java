package minesweeper_game;

import minesweeper_game.command.FlagCommand;
import minesweeper_game.command.RevealCommand;
import minesweeper_game.command.UnflagCommand;
import minesweeper_game.entities.Game;
import minesweeper_game.enums.GameStatus;
import minesweeper_game.observer.ConsoleView;

public class GameDemo {
    public static void main(String[] args) {
        // Get the Singleton instance of the game engine
        MinesweeperSystem system = MinesweeperSystem.getInstance();

        // Create a new game using the fluent builder
        system.createNewGame(10, 10, 10);

        // Add an observer to log game state changes
        system.addObserver(new ConsoleView());

        Game game = system.getGame(); // For direct command creation

        System.out.println("--- Initial Board ---");

        // --- Hardcoded Sequence of Moves ---

        // 1. Reveal a cell that is likely a zero to show the cascade
        System.out.println(">>> Action: Reveal (5, 5)");
        system.processMove(new RevealCommand(game, 5, 5));

        // 2. Flag a cell
        System.out.println(">>> Action: Flag (0, 0)");
        system.processMove(new FlagCommand(game, 0, 0));

        // 3. Try to reveal the flagged cell (should do nothing)
        System.out.println(">>> Action: Reveal flagged cell (0, 0) - Should fail");
        system.processMove(new RevealCommand(game, 0, 0));

        // 4. Unflag the cell
        System.out.println(">>> Action: Unflag (0, 0)");
        system.processMove(new UnflagCommand(game, 0, 0));

        // 5. Reveal another cell, possibly a number
        System.out.println(">>> Action: Reveal (1, 1)");
        system.processMove(new RevealCommand(game, 1, 1));

        // 6. Deliberately hit a mine to end the game
        // This is tricky with random placement. We'll just click around until we hit one or win.
        boolean gameOver = false;
        for (int r = 0; r < 10 && !gameOver; r++) {
            for (int c = 0; c < 10 && !gameOver; c++) {
                if (!game.getBoard().getCell(r, c).isRevealed()) {
                    System.out.println(">>> Action: Reveal (" + r + ", " + c + ")");
                    system.processMove(new RevealCommand(game, r, c));
                    if (system.getGameStatus() == GameStatus.LOST) {
                        System.out.println("BOOM! Game Over.");
                        gameOver = true;
                    }
                    if (system.getGameStatus() == GameStatus.WON) {
                        System.out.println("CONGRATULATIONS! You won.");
                        gameOver = true;
                    }
                }
            }
        }

        System.out.println("\n--- Final Board State ---");
    }
}
