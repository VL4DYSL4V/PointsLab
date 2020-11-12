package algorithm;

import entity.Pair;
import entity.Point;
import entity.Point2D;
import enums.CoordinateConstraints;
import util.GeometryUtil;

import java.util.*;

public class KeylKircpatrik implements PointConnector {

    @Override
    public Collection<Pair<Point2D>> getLinesPoints(Collection<Point2D> points) {
        ArrayList<Point2D> input = new ArrayList<>(points);
        input.sort(Comparator.comparingInt(Point2D::getY).thenComparingInt(Point2D::getX));
        Pair<ArrayList<Point2D>> halves = getLeftAndRight(input);
        ArrayList<Point2D> left = halves.getFirst();
        ArrayList<Point2D> right = halves.getSecond();
//        System.out.println(halves);
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
    private ArrayList<Pair<Point2D>> connectPoints(ArrayList<Point2D> left, ArrayList<Point2D> right) {
        ArrayList<Pair<Point2D>> out = new ArrayList<>();
        Point2D lastLeft = left.get(0);
        Point2D lastVector = GeometryUtil.I;
        Map<Double, Point2D> leftAngles = new HashMap<>();

        for (int j = 1; j < left.size(); j++) {
            Point2D curr = left.get(j);
            Point2D vector = new Point(lastLeft.getX() - curr.getX(), lastLeft.getY() - curr.getY());
            double angle = GeometryUtil.getAngle(vector, GeometryUtil.I);
            System.out.println("angle for " + lastLeft + ", " + curr + " = " + angle);
            leftAngles.put(angle, curr);
        }
//            System.out.println(leftAngles);
        Point2D curr = leftAngles.get(leftAngles.keySet().stream().min(Comparator.naturalOrder()).get());
        out.add(new Pair<>(lastLeft, curr));
        lastLeft = curr;


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

    //    private Pair<ArrayList<Point2D>> getLeftAndRight(ArrayList<Point2D> sortedInput) {
//        ArrayList<Point2D> left = new ArrayList<>();
//        ArrayList<Point2D> right = new ArrayList<>();
//        Point2D last = sortedInput.get(0);
//        left.add(last);
//        for (int i = 1; i < sortedInput.size(); i++) {
//            Point2D curr = sortedInput.get(i);
//            if (curr.getY() != last.getY()) {
//                left.add(curr);
//                if (!left.contains(last)) {
//                    right.add(last);
//                }
//            }
//            last = curr;
//        }
//        if (!left.get(left.size() - 1).equals(last)) {
//            right.add(last);
//        }
//        return new Pair<>(left, right);
//    }
    private Pair<ArrayList<Point2D>> getLeftAndRight(ArrayList<Point2D> sortedInput) {
        ArrayList<Point2D> left = new ArrayList<>();
        ArrayList<Point2D> right = new ArrayList<>();
        for (Point2D point2D : sortedInput) {
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
}
