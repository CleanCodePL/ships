package ships.factory;

import ships.controller.GameInterface;
import ships.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class GameBuilder {

    private int height;
    private int width;
    private List<ShipType> ships = new ArrayList<>();
    private GameInterface gameInterface;

    public GameBuilder withBoardSize(int width, int height) {
        this.height = height;
        this.width = width;
        return this;
    }

    public GameBuilder withShip(int size, Orientation orientation) {
        this.ships.add(new ShipType(size, orientation));
        return this;
    }

    public GameBuilder withGameInterface(GameInterface gameInterface) {
        this.gameInterface = gameInterface;
        return this;
    }

    public Game build() {
        List<Ship> ships = buildShips();
        Board board = new Board(width, height);

        locateShipsOnBoard(ships, board);

        return new Game(board, gameInterface);
    }

    private void locateShipsOnBoard(List<Ship> ships, Board board) {
        ships.forEach(ship -> {
            Set<Point> availableLocations = board.findPlaceForShip(ship);
            Point headPoint = availableLocations.stream()
                    .skip(new Random().nextInt(availableLocations.size()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("No available place"));
            ship.translate(headPoint);
            board.addShip(ship);
        });
    }

    private List<Ship> buildShips() {
        return this.ships.stream().map(ShipFactory::createShip).collect(Collectors.toList());
    }

    static class ShipType {
        private final int size;
        private final Orientation orientation;

        ShipType(int size, Orientation orientation) {
            if (size < 1) {
                throw new IllegalArgumentException("Size can not be smaller than 1");
            }

            this.size = size;
            this.orientation = orientation;
        }

        int getSize() {
            return size;
        }

        Orientation getOrientation() {
            return orientation;
        }
    }

}
