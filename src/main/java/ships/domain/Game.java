package ships.domain;

import ships.controller.GameInterface;

public class Game {

    private final Board board;
    private final GameInterface gameInterface;

    public Game(Board board, GameInterface gameInterface) {
        this.board = board;
        this.gameInterface = gameInterface;
    }

    public void start() {
        long startTime = System.currentTimeMillis();
        while (!this.board.allShipsSunk()) {
            gameInterface.drawBoard(this.board);
            Point shoot = gameInterface.shoot();
            this.board.shoot(shoot);
        }
        long gameDuration = System.currentTimeMillis() - startTime;
        gameInterface.statistics(gameDuration, this.board.countShoots());
    }
}
