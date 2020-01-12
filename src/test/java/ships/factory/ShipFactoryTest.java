package ships.factory;

import org.junit.jupiter.api.Test;
import ships.domain.Orientation;
import ships.domain.Ship;

import static org.junit.jupiter.api.Assertions.*;

class ShipFactoryTest {

    @Test
    void createHorizontalShip() {
        ShipType horizontalShipType = new ShipType(3, Orientation.HORIZONTAL);
        Ship horizontalShip = ShipFactory.createShip(horizontalShipType);
        assertEquals(horizontalShipType.getSize(), horizontalShip.getPosition().size());
        horizontalShip.getPosition().forEach(point -> assertEquals(0, point.getY()));
    }

    @Test
    void createVerticalShip() {
        ShipType verticalShipType = new ShipType(3, Orientation.VERTICAL);
        Ship verticalShip = ShipFactory.createShip(verticalShipType);
        assertEquals(verticalShipType.getSize(), verticalShip.getPosition().size());
        verticalShip.getPosition().forEach(point -> assertEquals(0, point.getX()));
    }

    @Test
    void createNotEqualsShips() {
        int size = 3;
        Ship verticalShip = ShipFactory.createShip(new ShipType(size, Orientation.VERTICAL));
        Ship horizontalShip = ShipFactory.createShip(new ShipType(size, Orientation.HORIZONTAL));
        assertNotEquals(verticalShip, horizontalShip);
    }

}