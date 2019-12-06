package ships.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
        if (!this.ships.add(ship)) {
            throw new IllegalStateException("Cannot locate two ships in this same place!");
        }
    }

    public Set<Point> findPlaceForShip(Ship ship) {
        final Set<Point> reservedPoints = shipPoints();
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
                .noneMatch(shipPoint -> {
                    Point checkedPoint = point.add(shipPoint);
                    return reservedPoints.contains(checkedPoint) && isPointInBoard(checkedPoint);
                });
    }

    private boolean isPointInBoard(Point checkedPoint) {
        return checkedPoint.getX() < width && checkedPoint.getY() < height;
    }

    private Set<Point> shipPoints() {
        return this.ships.stream().flatMap(ship -> ship.getPosition().stream()).collect(Collectors.toSet());
    }

}
