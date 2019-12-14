package ships.controller.impl.console;

import ships.controller.GameInterface;
import ships.domain.Board;
import ships.domain.Point;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class ConsoleGameInterface implements GameInterface {

    private Scanner in = new Scanner(System.in);

    @Override
    public void drawBoard(Board board) {
        Set<Point> sunkenShips = board.getSunkenShips().stream()
                .flatMap(ship -> ship.getPosition().stream())
                .collect(Collectors.toSet());

        PointType[][] markedBoard = prepareEmptyBoard(board.getWidth(), board.getHeight());
        fillWithPoints(markedBoard, board.getHitPoints(), PointType.HIT);
        fillWithPoints(markedBoard, board.getMissedShoots(), PointType.MISSED);
        fillWithPoints(markedBoard, sunkenShips, PointType.SUNK);

        System.out.println(boardToString(markedBoard));
    }

    private void fillWithPoints(PointType[][] board, Set<Point> points, PointType type) {
        points.forEach(point -> {
            Array.set(board[point.getY()], point.getX(), type);
        });
    }

    private String boardToString(PointType[][] board) {
        StringBuilder boardBuilder = new StringBuilder();
        char currentRowCharacter = 'A';

        for (PointType[] row : board) {
            for (PointType column : row) {
                boardBuilder.append(column);
            }
            boardBuilder.append(currentRowCharacter++).append('\n');
        }

        int numberOfColumns = board[0].length;
        for (int i = 1; i <= numberOfColumns; i++) {
            boardBuilder.append(i);
        }

        return boardBuilder.toString();
    }

    private PointType[][] prepareEmptyBoard(int width, int height) {
        PointType[][] markedBoard = new PointType[height][width];
        for (PointType[] row : markedBoard) {
            Arrays.fill(row, PointType.EMPTY);
        }
        return markedBoard;
    }

    @Override
    public Point shoot() {
        System.out.print("Coordinates: ");
        int y = in.next().toUpperCase().charAt(0) - 'A';
        int x = in.nextInt() - 1;
        return new Point(x, y);
    }

    @Override
    public void showStatistics(long gameDuration, int countShoots) {
        System.out.println("|---------------- Statistics ------------------|");
        System.out.println("Game duration: " + gameDuration / 1000 + "s");
        System.out.println("Shoots: " + countShoots);
    }

    @Override
    protected void finalize() throws Throwable {
        in.close();
        super.finalize();
    }
}
