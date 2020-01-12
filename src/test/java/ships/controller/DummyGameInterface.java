package ships.controller;

import ships.domain.Board;
import ships.domain.Point;

public class DummyGameInterface implements GameInterface {

    private final int width;
    private final int height;

    private int lastX = 0;
    private int lastY = 0;


    public DummyGameInterface() {
        this.height = 0;
        this.width = 0;
    }

    public DummyGameInterface(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void drawBoard(Board board) {

    }

    @Override
    public Point shoot() {
        if (width == ++lastX) {
            this.lastX = 0;
            this.lastY++;
        }

        if (lastY > height || lastX > width) {
            throw new IllegalStateException("Check yours tests logic!");
        }

        return new Point(lastX, lastY);
    }

    @Override
    public void showStatistics(long gameDuration, int countShoots) {

    }
}
