package ships.factory;

import org.junit.jupiter.api.Test;
import ships.controller.DummyGameInterface;
import ships.domain.Orientation;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameBuilderTest {

    @Test
    void buildGame() {
        GameBuilder builder = new GameBuilder();
        int shipSize = 2;
        int boardX = 4;
        int boardY = 4;

        builder.withShip(shipSize, Orientation.VERTICAL);
        builder.withBoardSize(boardX, boardY);
        assertThrows(IllegalStateException.class, builder::build);

        builder.withGameInterface(new DummyGameInterface());
        assertNotNull(builder.build());
    }

    @Test
    void badBoardSizes() {
        GameBuilder builder = new GameBuilder()
                .withBoardSize(0, 0)
                .withGameInterface(new DummyGameInterface());
        assertThrows(IllegalArgumentException.class, builder::build);
    }

}