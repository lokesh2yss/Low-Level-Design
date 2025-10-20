package minesweeper_game.command;

import minesweeper_game.entities.Game;

public class UnflagCommand implements MoveCommand{
    private final Game game;
    private final int row;
    private final int col;

    public UnflagCommand(Game game, int row, int col) {
        this.game = game;
        this.row = row;
        this.col = col;
    }

    @Override
    public void execute() {
        game.unflagCell(row, col);
    }
}
