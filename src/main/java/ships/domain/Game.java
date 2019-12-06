package ships.domain;

import ships.controller.GameInterface;

public class Game {

    private final Board board;
    private final GameInterface gameInterface;

    public Game(Board board, GameInterface gameInterface) {
        this.board = board;
        this.gameInterface = gameInterface;
    }

    public void run() {
        boolean gameIsActive = true;
        while (gameIsActive) {
            gameInterface.drawBoard(this.board);
        }
    }
}
