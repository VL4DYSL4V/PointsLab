package algorithm;

import util.Pair;
import entity.Point2D;

import java.util.Collection;

public interface PointConnector {

     Collection<Pair<Point2D>> getLinesPoints(Collection<? extends Point2D> points);
}
