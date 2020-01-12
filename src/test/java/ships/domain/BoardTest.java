package ships.domain;

import org.junit.jupiter.api.Test;
import ships.factory.ShipFactory;
import ships.factory.ShipType;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private final ShipType type = new ShipType(3, Orientation.VERTICAL);
    private final Ship verticalTripleShip = ShipFactory.createShip(type);

    @Test
    void twoShipsInThisSamePlace() {
        int size = 3;
        Board board = new Board(size, size);
        board.addShip(verticalTripleShip);
        assertThrows(IllegalStateException.class, () -> board.addShip(verticalTripleShip));
    }

    @Test
    void shipNotInBoard() {
        int size = 2;
        Board board = new Board(size, size);
        assertThrows(IllegalStateException.class, () -> board.addShip(verticalTripleShip));
    }

    @Test
    void allShipsSunken() {
        int width = 3;
        int height = 3;
        Board board = new Board(width, height);
        board.addShip(verticalTripleShip);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                board.shoot(new Point(i, j));
            }
        }

        assertTrue(board.allShipsSunk());
        int countOfFields = width * height;
        assertEquals(countOfFields, board.countShoots());
        assertEquals(countOfFields - verticalTripleShip.getPosition().size(), board.getMissedShoots().size());
    }

}