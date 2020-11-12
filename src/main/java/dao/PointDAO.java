package dao;

import entity.ColorPoint;
import entity.Point;

import java.awt.*;
import java.util.Collection;

public interface PointDAO {

    Collection<Point> getPoints(int amount);

    Collection<ColorPoint> getColorPoints(int amount, Color color);
}
