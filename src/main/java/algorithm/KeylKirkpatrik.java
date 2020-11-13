package algorithm;

import util.Pair;
import entity.Point;
import enums.CoordinateConstraints;
import util.GeometryUtil;

import java.util.*;

public class KeylKirkpatrik implements PointConnector {

    @Override
    public Collection<Pair<Point>> getLinesPoints(Collection<Point> points) {
        ArrayList<Point> input = new ArrayList<>(points);
        input.sort(Comparator.comparingInt(Point::getY).thenComparingInt(Point::getX));
        Pair<ArrayList<Point>> halves = getLeftAndRight(input);
        ArrayList<Point> left = halves.getFirst();
        ArrayList<Point> right = halves.getSecond();
        System.out.println("left = " + left);
        System.out.println("right = " + right);
        return connectPoints(left, right);
    }

    //    private ArrayList<Pair<Point2D>> connectPoints(ArrayList<Point2D> left, ArrayList<Point2D> right) {
//        ArrayList<Pair<Point2D>> out = new ArrayList<>();
//        Point2D lastLeft = left.get(0);
//        for (int i = 1; i < left.size(); i++) {
//            Point2D curr = left.get(i);
//            out.add(new Pair<>(lastLeft, curr));
//            lastLeft = curr;
//        }
//
//        Point2D lastRight = right.get(0);
//        for (int i = 1; i < right.size(); i++) {
//            Point2D curr = right.get(i);
//            out.add(new Pair<>(lastRight, curr));
//            lastRight = curr;
//        }
//
//        return out;
//    }
    private ArrayList<Pair<Point>> connectPoints(ArrayList<Point> left, ArrayList<Point> right) {
        ArrayList<Pair<Point>> out = new ArrayList<>();
        Point lastLeft = left.get(0);
        Point lastVector = GeometryUtil.I;
        Map<Double, Point> leftAngles = new HashMap<>();
        for(int i = 1; i < left.size(); i++){
            Point curr = left.get(i);
            Point vector = new Point(lastLeft.getX() - curr.getX(), lastLeft.getY() - curr.getY());
            double angle = GeometryUtil.getAngle(vector, GeometryUtil.I);
        }


//        for (int i = 1; i < left.size(); i++) {
//            for (int j = i; j < left.size(); j++) {
//                Point2D curr = left.get(j);
//                Point2D vector = new Point(lastLeft.getX() - curr.getX(), lastLeft.getY() - curr.getY());
//                double angle = GeometryUtil.getAngle(vector, GeometryUtil.I);
//                System.out.println("angle for " + lastLeft + ", " + curr +" = " + angle);
//                leftAngles.put(angle, curr);
//            }
////            System.out.println(leftAngles);
//            Point2D curr = leftAngles.get(leftAngles.keySet().stream().min(Comparator.naturalOrder()).get());
//            out.add(new Pair<>(lastLeft, curr));
//            lastLeft = curr;
//        }

//        Point2D lastRight = right.get(0);
//        for (int i = 1; i < right.size(); i++) {
//            Point2D curr = right.get(i);
//            out.add(new Pair<>(lastRight, curr));
//            lastRight = curr;
//        }


        return out;
    }

    private Pair<ArrayList<Point>> getLeftAndRight(ArrayList<Point> sortedInput) {
        ArrayList<Point> left = new ArrayList<>();
        ArrayList<Point> right = new ArrayList<>();
        for (Point point2D : sortedInput) {
            if (point2D.getX() <= CoordinateConstraints.MIDDLE_X.getValue()) {
                if (left.isEmpty() || left.get(left.size() - 1).getY() < point2D.getY()) {
                    left.add(point2D);
                } else if (left.get(left.size() - 1).getX() > point2D.getX()) {
                    left.set(left.size() - 1, point2D);
                }
            } else {
                if (right.isEmpty() || right.get(right.size() - 1).getY() < point2D.getY()) {
                    right.add(point2D);
                } else if (right.get(right.size() - 1).getX() < point2D.getX()) {
                    right.set(right.size() - 1, point2D);
                }
            }
        }
        return new Pair<>(left, right);
    }

    @Override
    public String toString() {
        return "KeylKirkpatrik{}";
    }
}
