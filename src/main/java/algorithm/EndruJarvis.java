package algorithm;

import util.Pair;
import entity.Point2D;
import util.GeometryUtil;

import java.util.*;
import java.util.stream.Collectors;

public class EndruJarvis implements PointConnector {
    @Override
    public Collection<Pair<Point2D>> getLinesPoints(Collection<? extends Point2D> points) {
        List<Point2D> input = new ArrayList<>(points);
        input.sort(Comparator.comparingInt(Point2D::getX).thenComparingInt(Point2D::getY));
        Point2D left = input.get(0);
        Point2D right = input.get(input.size() - 1);
        List<Point2D> upper = input.stream().filter(p ->
                GeometryUtil.compareToLine(left, right, p) >= 0).collect(Collectors.toList());
        List<Point2D> lower = input.stream().filter(p ->
                GeometryUtil.compareToLine(left, right, p) < 0).collect(Collectors.toList());
        List<Pair<Point2D>> first = getPairs(upper, input);
        List<Pair<Point2D>> second = getPairs(lower, input);
        Collection<Pair<Point2D>> out = new ArrayList<>();
        if (first.size() > 1 && second.size() > 1) {
            Point2D lastFirst = first.get(first.size() - 1).getSecond();
            Point2D lastSecond = second.get(second.size() - 1).getSecond();
            out.add(new Pair<>(lastFirst, lastSecond));
        } else if (second.size() == 1) {
            out.add(new Pair<>(first.get(0).getFirst(), second.get(0).getFirst()));
            out.add(new Pair<>(first.get(first.size() - 1).getSecond(), second.get(0).getSecond()));
        } else if (second.isEmpty()) {
            out.add(new Pair<>(first.get(0).getFirst(), first.get(0).getFirst()));
            out.add(new Pair<>(first.get(first.size() - 1).getSecond(), first.get(0).getSecond()));
        }
        out.addAll(first);
        out.addAll(second);
        return out;
    }

    private List<Pair<Point2D>> getPairs(List<Point2D> half, List<Point2D> input) {
        List<Pair<Point2D>> out = new ArrayList<>();
        if (half.size() == 0) {
            return out;
        }
        if (half.size() == 1) {
            out.add(new Pair<>(half.get(0), half.get(0)));
        } else {
            Point2D last = input.get(0);
            for (Point2D curr : half) {
                Set<Integer> compResults = new HashSet<>();
                for (Point2D other : input) {
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
}
