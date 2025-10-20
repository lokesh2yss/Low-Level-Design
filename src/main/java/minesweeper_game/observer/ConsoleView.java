package minesweeper_game.observer;

import minesweeper_game.entities.Game;
import minesweeper_game.enums.GameStatus;

public class ConsoleView implements GameObserver{
    @Override
    public void update(Game game) {
        printBoard(game);
        if(game.getStatus() == GameStatus.WON) {
            System.out.println("Congratulation! you won the game");
        }
        else {
            System.out.println("Game Over! You hit a mine");
        }
    }
    private void printBoard(Game game) {
        // Simple clear screen for console
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.print("  ");
        for (int c = 0; c < game.getCols(); c++) {
            System.out.print(c + " ");
        }
        System.out.println();

        for (int r = 0; r < game.getRows(); r++) {
            System.out.print(r + " ");
            for (int c = 0; c < game.getCols(); c++) {
                System.out.print(game.getCellDisplayChar(r, c) + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------");
    }
}
