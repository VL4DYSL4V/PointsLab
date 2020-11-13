package dao;

import entity.Point;
import enums.CoordinateConstraints;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

public class HardCodePointDAO implements PointDAO {

    private static final int MAX_POINT_AMOUNT = CoordinateConstraints.X_COORDS_AMOUNT.getValue()
            * (CoordinateConstraints.Y_COORDS_AMOUNT.getValue());

    /**
     * Returns a collection of {@link Point} with
     * both coordinates between 0 and 9 including
     */
    @Override
    public Collection<Point> getPoints(int amount) {
        if (amount < 0 || amount >= MAX_POINT_AMOUNT) {
            throw new IllegalArgumentException("Amount is too big");
        }
        Collection<Point> out = new LinkedList<>();
        Random random = new Random();
        for (int i = 0; i < amount; i++) {
            Point point = new Point(random.nextInt(CoordinateConstraints.X_COORDS_AMOUNT.getValue()),
                                    random.nextInt(CoordinateConstraints.Y_COORDS_AMOUNT.getValue()));
            while (out.contains(point)) {
                point = new Point(random.nextInt(CoordinateConstraints.X_COORDS_AMOUNT.getValue()),
                        random.nextInt(CoordinateConstraints.Y_COORDS_AMOUNT.getValue()));
            }
            out.add(point);
        }
        return out;
    }

//    @Override
//    public Collection<Point> getPoints(int amount) {
//        Collection<Point> out = new LinkedList<>();
//        out.add(new Point(0, 9));
//        out.add(new Point(0, 1));
//        out.add(new Point(0, 2));
//
//        out.add(new Point(0, 3));
//        out.add(new Point(4, 1));
//        out.add(new Point(3, 8));
//
//        out.add(new Point(8, 7));
//        out.add(new Point(9, 1));
//        return out;
//    }

}
