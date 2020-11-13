package algorithm;

import entity.Point;
import util.Pair;

import java.util.Collection;

public interface PointConnector {

     Collection<Pair<Point>> getLinesPoints(Collection<Point> points);
}
