package ships.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {

    private final int width;
    private final int height;

    private Set<Point> shootedPoints = new HashSet<>();
    private Set<Ship> ships = new HashSet<>();

    public Board(int width, int height) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("Cannot create board with given sizes!");
        }

        this.width = width;
        this.height = height;
    }

    public void addShip(Ship ship) {
        if (!ship.getPosition().stream().allMatch(this::isPointBelongsToBoard)) {
            throw new IllegalStateException("Ship must be placed on the map!");
        }

        if (!this.ships.add(ship)) {
            throw new IllegalStateException("Cannot locate two ships in this same place!");
        }
    }

    public Set<Point> findPlaceForShip(Ship ship) {
        final Set<Point> reservedPoints = getShipPoints();
        Set<Point> availablePoints = new HashSet<>();

        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                Point boardPoint = new Point(w, h);
                if (canBeLocatedInPlace(boardPoint, ship, reservedPoints)) {
                    availablePoints.add(boardPoint);
                }
            }
        }

        return availablePoints;
    }

    private boolean canBeLocatedInPlace(Point point, Ship ship, final Set<Point> reservedPoints) {
        return ship.getPosition()
                .stream()
                .allMatch(shipPoint -> {
                    Point checkedPoint = point.add(shipPoint);
                    return !reservedPoints.contains(checkedPoint) && isPointBelongsToBoard(checkedPoint);
                });
    }

    private Set<Point> getShipPoints() {
        return this.ships.stream().flatMap(ship -> ship.getPosition().stream()).collect(Collectors.toSet());
    }

    void shoot(Point point) {
        this.shootedPoints.add(point);
    }

    boolean allShipsSunk() {
        Set<Point> shipPoints = getShipPoints();
        return this.shootedPoints.containsAll(shipPoints);
    }

    public Set<Ship> getSunkenShips() {
        return this.ships.stream()
                .filter(ship -> this.shootedPoints.containsAll(ship.getPosition()))
                .collect(Collectors.toSet());
    }

    public Set<Point> getHitPoints() {
        Set<Ship> sunkenShips = getSunkenShips();

        Stream<Point> unsunkenShips = this.ships.stream().filter(ship -> !sunkenShips.contains(ship)).flatMap(ship -> ship.getPosition().stream());
        Set<Point> points = unsunkenShips.filter(shipPoint -> this.shootedPoints.contains(shipPoint)).collect(Collectors.toSet());
        return points;
    }

    public Set<Point> getMissedShoots() {
        Set<Point> hitShoots = getHitPoints();
        return this.shootedPoints.stream()
                .filter(point -> !hitShoots.contains(point))
                .collect(Collectors.toSet());
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private boolean isPointBelongsToBoard(Point point) {
        return point.getX() < width &&
                point.getY() < height &&
                point.getX() >= 0 &&
                point.getY() >= 0;
    }

    int countShoots() {
        return this.shootedPoints.size();
    }
}
