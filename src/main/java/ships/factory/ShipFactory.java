package ships.factory;

import ships.domain.Orientation;
import ships.domain.Point;
import ships.domain.Ship;

import java.util.HashSet;
import java.util.Set;

class ShipFactory {

    static Ship createShip(GameBuilder.ShipType type) {
        Set<Point> points = new HashSet<>();

        for (int i = 0; i < type.getSize(); i++) {
            if (type.getOrientation().equals(Orientation.VERTICAL)) {
                points.add(new Point(0, i));
            } else {
                points.add(new Point(i, 0));
            }
        }

        return new Ship(points);
    }

}
