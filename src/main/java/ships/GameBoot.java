package ships;

import ships.controller.impl.console.ConsoleGameInterface;
import ships.domain.Orientation;
import ships.factory.GameBuilder;

public class GameBoot {

    public static void main(String[] args) {
        new GameBuilder()
                .withBoardSize(10, 10)
                .withShip(1, Orientation.HORIZONTAL)
                .withShip(1, Orientation.HORIZONTAL)
                .withShip(1, Orientation.HORIZONTAL)
                .withShip(2, Orientation.HORIZONTAL)
                .withShip(2, Orientation.VERTICAL)
                .withShip(3, Orientation.HORIZONTAL)
                .withShip(3, Orientation.VERTICAL)
                .withShip(4, Orientation.HORIZONTAL)
                .withGameInterface(new ConsoleGameInterface())
                .build()
                .start();
    }

}
