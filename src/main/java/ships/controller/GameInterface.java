package ships.controller;

import ships.domain.Board;
import ships.domain.Point;

public interface GameInterface {

    void drawBoard(Board board);

    Point shoot();

    void statistics(long gameDuration, int countShoots);
}
