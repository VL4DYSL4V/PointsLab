package algorithm;

import entity.Pair;
import entity.Point2D;

import java.util.Collection;

public interface PointConnector {

     Collection<Pair<Point2D>> getLinesPoints(Collection<Point2D> points);
}
