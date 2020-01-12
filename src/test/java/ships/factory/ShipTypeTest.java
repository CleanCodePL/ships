package ships.factory;

import org.junit.jupiter.api.Test;
import ships.domain.Orientation;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ShipTypeTest {

    @Test
    void createBadShipType() {
        assertThrows(IllegalArgumentException.class, () -> new ShipType(0, Orientation.VERTICAL));
    }

}