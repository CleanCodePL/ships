package ships.domain;

import java.util.Objects;
import java.util.Set;

public class Ship {

    private Set<Point> position;

    public Ship(Set<Point> position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return Objects.equals(position, ship.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
