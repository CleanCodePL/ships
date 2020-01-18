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
    private List<ShipType> shipTypes = new ArrayList<>();
    private GameInterface gameInterface;

    public GameBuilder withBoardSize(int width, int height) {
        this.height = height;
        this.width = width;
        return this;
    }

    public GameBuilder withShip(int size, Orientation orientation) {
        this.shipTypes.add(new ShipType(size, orientation));
        return this;
    }

    public GameBuilder withGameInterface(GameInterface gameInterface) {
        this.gameInterface = gameInterface;
        return this;
    }

    public Game build() {
        if (this.gameInterface == null) {
            throw new IllegalStateException("Cannot build game without interface!");
        }

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
        return this.shipTypes.stream().map(ShipFactory::createShip).collect(Collectors.toList());
    }

}
