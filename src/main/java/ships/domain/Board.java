package ships.domain;

import java.util.HashSet;
import java.util.Set;

public class Board {

    private int width;
    private int height;

    private Set<Point> shootedPoints = new HashSet<>();
    private Set<Ship> ships = new HashSet<>();

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
