package snake_ladder;

import snake_ladder.entities.Game;
import snake_ladder.entities.Ladder;
import snake_ladder.entities.Player;
import snake_ladder.entities.Snake;
import snake_ladder.enums.Symbol;

public class SnakeLadderDemo {
    public static void main(String[] args) {
        Player player1 = new Player("Alice", Symbol.O);
        Player player2 = new Player("Bob", Symbol.X);
        SnakeLadderSystem system = SnakeLadderSystem.getInstance();
        system.createGame(player1, player2);
        Snake[] snakes = new Snake[2];
        Ladder[] ladders = new Ladder[2];

        snakes[0] = new Snake(1 ,3, 4, 9);
        snakes[1] = new Snake(3, 2, 5, 2);
        ladders[0] = new Ladder(6, 4, 9, 3);
        ladders[1] = new Ladder(5, 5, 7,7);


        system.addSnakes(snakes);
        system.addLadders(ladders);
        system.makeMove(player1, 5, 5);

    }
}
