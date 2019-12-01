package ships.factory;

import ships.domain.Point;
import ships.domain.Ship;

import java.util.HashSet;
import java.util.Set;

public class ShipFactory {

    public static Ship createShip(Point headPoint, int size, boolean isVertical) {
        if (size < 1) {
            throw new IllegalStateException();
        }

        Set<Point> points = new HashSet<>();
        points.add(headPoint);

        for (int i = 1; i < size; i++) {
            if (isVertical) {
                points.add(new Point(headPoint.getX(), headPoint.getY() + i));
            } else {
                points.add(new Point(headPoint.getX() + i, headPoint.getY()));
            }
        }

        return new Ship(points);
    }

}
