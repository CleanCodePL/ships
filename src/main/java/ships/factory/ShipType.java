package ships.factory;

import ships.domain.Orientation;

public class ShipType {
    private final int size;
    private final Orientation orientation;

    public ShipType(int size, Orientation orientation) {
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
