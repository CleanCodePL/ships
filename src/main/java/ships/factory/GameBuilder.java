package ships.factory;

import ships.domain.Board;
import ships.domain.Game;

public class GameBuilder {

    private Game game = new Game();

    public GameBuilder withBoardSize(int width, int height) {
        game.setBoard(new Board(width, height));
        return this;
    }

}
