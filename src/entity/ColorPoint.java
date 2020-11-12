package entity;

import java.awt.*;
import java.util.Objects;

public final class ColorPoint implements Point2D{

    private final Point point;
    private final Color color;

    public ColorPoint() {
        this(new Point(), new Color(0,0,0));
    }

    public ColorPoint(Point point, Color color) {
        this.point = point;
        this.color = color;
    }

    public Point getPoint() {
        return point;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColorPoint that = (ColorPoint) o;
        return Objects.equals(point, that.point) &&
                Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, color);
    }

    @Override
    public String toString() {
        return
//                "ColorPoint{" +
                "point=" + point +
//                ", color=" + color +
                '}';
    }

    @Override
    public int getX() {
        return point.getX();
    }

    @Override
    public int getY() {
        return point.getY();
    }
}
