package algorithm;

import entity.Point;
import util.GeometryUtil;
import util.Pair;

import java.util.*;

public class Recursive implements PointConnector {
    @Override
    public Collection<Pair<Point>> getLinesPoints(Collection<Point> points) {
//        Collection<Pair<Point>> out = new LinkedList<>();
        List<Point> convexHull = new LinkedList<>(getConvexHull(points));
//        convexHull.sort(Comparator.comparingInt(Point::getX));
//        for(int i = 0; i < convexHull.size() - 1; i++){
//            out.add(new Pair<>(convexHull.get(i), convexHull.get(i + 1)));
//        }
//        out.add(new Pair<>(convexHull.get(0), convexHull.get(convexHull.size() - 1)));
//        return out;
//        System.out.println(convexHull);
        return new EndruJarvis().getLinesPoints(convexHull);
    }

    private Collection<Point> getConvexHull(Collection<Point> points) {
        Collection<Point> out = new HashSet<>();
        if (points.isEmpty()) {
            return out;
        } else if (points.size() < 4) {
            return new HashSet<>(points);
        }

        List<Point> input = new ArrayList<>(points);
        input.sort(Comparator.comparingInt(Point::getX).thenComparingInt(Point::getY));
        Point left = input.get(0);
        Point right = input.get(input.size() - 1);
        out.add(left);
        out.add(right);
        //Если они все на одной прямой, то получается СтэкОверфлоу
        if(input.stream().noneMatch(p -> GeometryUtil.compareToLine(left, right, p) != 0)){
            return new HashSet<>(input);
        }
        List<Point> upper = GeometryUtil.getUpperAndEqualSubset(left, right, input);
        List<Point> lower = GeometryUtil.getLowerAndEqualSubset(left, right, input);
        Map<Double, Point> squarePointUpperMap = getSquarePointMap(left, right, upper);
        Map<Double, Point> squarePointLowerMap = getSquarePointMap(left, right, lower);
        if (!squarePointUpperMap.isEmpty()) {
            Point withMaxSquareUpper = squarePointUpperMap.get(
                    squarePointUpperMap.keySet().stream().max(Comparator.naturalOrder()).orElseThrow(
                            IllegalStateException::new
                    )
            );
            out.add(withMaxSquareUpper);
            out.addAll(getConvexHull(GeometryUtil.getUpperAndEqualSubset(left, withMaxSquareUpper, upper)));
            out.addAll(getConvexHull(GeometryUtil.getUpperAndEqualSubset(withMaxSquareUpper, right, upper)));
        }
        if (!squarePointLowerMap.isEmpty()) {
            Point withMaxSquareLower = squarePointLowerMap.get(
                    squarePointLowerMap.keySet().stream().max(Comparator.naturalOrder()).orElseThrow(
                            IllegalStateException::new
                    )
            );
            out.add(withMaxSquareLower);
            out.addAll(getConvexHull(GeometryUtil.getLowerAndEqualSubset(left, withMaxSquareLower, lower)));
            out.addAll(getConvexHull(GeometryUtil.getLowerAndEqualSubset(withMaxSquareLower, right, lower)));
        }
        return out;
    }

    private HashMap<Double, Point> getSquarePointMap(Point left, Point right, List<Point> halfOfInput) {
        HashMap<Double, Point> out = new HashMap<>();
        for (int i = 1; i < halfOfInput.size() - 1; i++) {
            Point curr = halfOfInput.get(i);
            Double square = GeometryUtil.getTriangleSquare(left, right, curr);
            out.put(square, curr);
        }
        return out;
    }

}
