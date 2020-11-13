package dao;

import entity.Point;

import java.util.Collection;

public interface PointDAO {

    Collection<Point> getPoints(int amount);

}
