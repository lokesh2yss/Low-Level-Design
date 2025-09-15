package snake_ladder.state;

import snake_ladder.entities.Game;
import snake_ladder.entities.Player;
import snake_ladder.enums.GameStatus;
import snake_ladder.enums.Symbol;
import snake_ladder.exceptions.InvalidMoveException;

public class InProgressState implements GameState{
    @Override
    public void handleMove(Game game, Player player, int row, int col) {
        if(game.getCurentPlayer() != player) {
            throw new InvalidMoveException("Not your turn");
        }
        if(game.getBoard().getCell(row, col).getSymbol() == Symbol.EMPTY) {
            player.setPosition(row, col);
        }
        else if(game.getBoard().getCell(row, col).getSymbol() == Symbol.SH) {
            int[] snakeTail = game.getBoard().getSnakeTail(row, col);
            player.setPosition(snakeTail[0], snakeTail[1]);
        }
        else if(game.getBoard().getCell(row, col).getSymbol() == Symbol.LT) {
            int[] ladderHead = game.getBoard().getLadderHead(row, col);
            player.setPosition(ladderHead[0], ladderHead[1]);
        }

        if(game.checkWinner(player)) {
            game.setWinner(player);
            game.setStatus(player.getSymbol() == Symbol.O? GameStatus.WINNER_O: GameStatus.WINNER_X);
            game.setState(new WinnerState());
        }
        else {
            game.switchPlayer();
        }

    }
}
