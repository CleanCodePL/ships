package ships.controller.impl.console;

public enum PointType {

    EMPTY(' '),
    MISSED('*'),
    HIT('@'),
    SUNK('#');

    private final Character mark;

    PointType(Character mark) {
        this.mark = mark;
    }

    public Character getMark() {
        return mark;
    }

    @Override
    public String toString() {
        return mark.toString();
    }
}
