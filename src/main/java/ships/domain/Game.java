package ships.domain;

public class Game {

    private final Board board;

    public Game(int boardHeight, int boardWidth, int singleShip, int doubleShip, int tripleShip) {
        this.board = new Board(boardHeight, boardWidth);

    }
}
