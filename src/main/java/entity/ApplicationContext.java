package entity;

import algorithm.EndruJarvis;
import algorithm.PointConnector;
import dao.PointDAO;
import enums.UIColor;
import util.Pair;

import java.awt.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;

public class ApplicationContext {

    private final PointDAO  pointDAO;
    private PointConnector pointConnector = new EndruJarvis();
    private Color pointTheme = UIColor.GREEN_THEME.getColor();
    private Collection<Point> points = new LinkedList<>();
    private Collection<Pair<Point>> lines = new LinkedList<>();

    public ApplicationContext(PointDAO pointDAO) {
        this.pointDAO = pointDAO;
    }

    public PointConnector getPointConnector() {
        return pointConnector;
    }

    public void setPointConnector(PointConnector pointConnector) {
        this.pointConnector = pointConnector;
    }

    public PointDAO getPointDAO() {
        return pointDAO;
    }

    public Color getPointTheme() {
        return pointTheme;
    }

    public void setPointTheme(Color pointTheme) {
        this.pointTheme = pointTheme;
    }

    public Collection<Point> getPoints() {
        return points;
    }

    public void setPoints(Collection<Point> points) {
        this.points = points;
    }

    public Collection<Pair<Point>> getLines() {
        return lines;
    }

    public void setLines(Collection<Pair<Point>> lines) {
        this.lines = lines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationContext that = (ApplicationContext) o;
        return Objects.equals(pointConnector, that.pointConnector) &&
                Objects.equals(pointDAO, that.pointDAO) &&
                Objects.equals(pointTheme, that.pointTheme) &&
                Objects.equals(points, that.points) &&
                Objects.equals(lines, that.lines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pointConnector, pointDAO, pointTheme, points, lines);
    }

    @Override
    public String toString() {
        return "ApplicationContext{" +
                "pointConnector=" + pointConnector +
                ", pointDAO=" + pointDAO +
                ", pointTheme=" + pointTheme +
                ", points=" + points +
                ", lines=" + lines +
                '}';
    }
}
