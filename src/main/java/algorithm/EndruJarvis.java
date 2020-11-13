package algorithm;

import entity.Point;
import util.Pair;
import util.GeometryUtil;

import java.util.*;


public class EndruJarvis implements PointConnector {

    @Override
    public Collection<Pair<Point>> getLinesPoints(Collection<Point> points) {
        List<Point> input = new ArrayList<>(points);
        input.sort(Comparator.comparingInt(Point::getX).thenComparingInt(Point::getY));
        Point left = input.get(0);
        Point right = input.get(input.size() - 1);
        List<Point> upper = GeometryUtil.getUpperAndEqualSubset(left, right, input);
        List<Point> lower = GeometryUtil.getLowerAndEqualSubset(left, right, input);
        List<Pair<Point>> first = getPairs(upper, input);
        List<Pair<Point>> second = getPairs(lower, input);
        Collection<Pair<Point>> out = new ArrayList<>();
        out.addAll(first);
        out.addAll(second);
        return out;
    }

    private List<Pair<Point>> getPairs(List<Point> half, List<Point> input) {
        List<Pair<Point>> out = new ArrayList<>();
        if (half.size() == 0) {
            return out;
        }
        if (half.size() == 1) {
            out.add(new Pair<>(half.get(0), half.get(0)));
        } else {
            Point last = input.get(0);
            for (Point curr : half) {
                Set<Integer> compResults = new HashSet<>();
                for (Point other : input) {
                    if (other.equals(curr) || other.equals(last)) {
                        continue;
                    }
                    double distance = GeometryUtil.compareToLine(last, curr, other);
                    int compRes = Double.compare(distance, 0);
                    if (compRes != 0) {
                        compResults.add(compRes);
                    }
                }
                if (compResults.size() != 2) {
                    out.add(new Pair<>(last, curr));
                    last = curr;
                }
            }
        }
        return out;
    }

    @Override
    public String toString() {
        return "EndruJarvis{}";
    }
}
