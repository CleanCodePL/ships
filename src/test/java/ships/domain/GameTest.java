package ships.domain;

import org.junit.jupiter.api.Test;
import ships.controller.DummyGameInterface;
import ships.factory.GameBuilder;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeout;

class GameTest {

    @Test
    void startGame() {
        int width = 3;
        int height = 3;

        Game game = new GameBuilder()
                .withGameInterface(new DummyGameInterface(width, height))
                .withBoardSize(width, height)
                .withShip(height, Orientation.VERTICAL)
                .build();
        assertTimeout(Duration.ofMillis(10000), game::start);
    }

}