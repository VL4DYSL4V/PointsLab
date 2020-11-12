package ui.guiElement;

import entity.ColorPoint;
import entity.Pair;
import entity.Point;
import entity.Point2D;
import enums.UIColor;
import enums.UIDimensionParameter;

import java.awt.*;
import java.util.Collection;
import java.util.LinkedList;

public class MainWindowCanvas extends Canvas {

    private Collection<ColorPoint> colorPoints = new LinkedList<>();
    private Collection<Pair<Point2D>> lineDefinitionPoints = new LinkedList<>();
    private final MainCanvasUtils mainCanvasUtils = new MainCanvasUtils();

    public MainWindowCanvas(){
        setBackground(UIColor.BACKGROUND.getColor());
        setForeground(UIColor.FOREGROUND.getColor());
        setSize(UIDimensionParameter.WIDTH.getValue(),
                Math.min(UIDimensionParameter.WIDTH.getValue(), UIDimensionParameter.HEIGHT.getValue()));
    }

    private class MainCanvasUtils {

        public int getCoordinateStretchingCoefficient() {
            return MainWindowCanvas.this.getWidth() / 10;
        }

        public int getDotWidth() {
            return MainWindowCanvas.this.getWidth() / 40;
        }

        public Point scaledPointOf(Point2D point) {
            int x = point.getX();
            int y = point.getY();
            x = (int) (x + 0.5) * getCoordinateStretchingCoefficient();
            y = (int) (y + 0.5) * getCoordinateStretchingCoefficient();
            return new Point(x, y);
        }
    }

    public void setLineDefinitionPoints(Collection<Pair<Point2D>> lineDefinitionPoints) {
        this.lineDefinitionPoints = lineDefinitionPoints;
    }

    public void setColorPoints(Collection<ColorPoint> colorPoints) {
        this.colorPoints = colorPoints;
    }

    public Collection<ColorPoint> getColorPoints() {
        return colorPoints;
    }

    @Override
    public void paint(Graphics g) {
        drawDots(g);
//        drawAllLines(g);
        drawLines(g);
    }

    private void drawDots(Graphics g) {
        Color colorBefore = g.getColor();
        for (ColorPoint colorPoint : colorPoints) {
            g.setColor(colorPoint.getColor());
            Point point = mainCanvasUtils.scaledPointOf(colorPoint.getPoint());
            g.fillOval(point.getX(), point.getY(), mainCanvasUtils.getDotWidth(), mainCanvasUtils.getDotWidth());
        }
        g.setColor(colorBefore);
    }

    private void drawLines(Graphics g){
        int shift = mainCanvasUtils.getDotWidth() / 2;
        for(Pair<Point2D> point2DPair: lineDefinitionPoints){
            int x1 = mainCanvasUtils.scaledPointOf(point2DPair.getFirst()).getX() + shift;
            int y1 = mainCanvasUtils.scaledPointOf(point2DPair.getFirst()).getY() + shift;
            int x2 = mainCanvasUtils.scaledPointOf(point2DPair.getSecond()).getX() + shift;
            int y2 = mainCanvasUtils.scaledPointOf(point2DPair.getSecond()).getY() + shift;
            drawLine(g, new Point(x1, y1), new Point(x2, y2), UIColor.CROSSING_LINE_THEME.getColor());
        }
    }

    private void drawAllLines(Graphics g) {
        int shift = mainCanvasUtils.getDotWidth() / 2;
        for (ColorPoint colorPoint : colorPoints) {
            for (ColorPoint colorPoint2 : colorPoints) {
                int x1 = mainCanvasUtils.scaledPointOf(colorPoint.getPoint()).getX() + shift;
                int y1 = mainCanvasUtils.scaledPointOf(colorPoint.getPoint()).getY() + shift;
                int x2 = mainCanvasUtils.scaledPointOf(colorPoint2.getPoint()).getX() + shift;
                int y2 = mainCanvasUtils.scaledPointOf(colorPoint2.getPoint()).getY() + shift;
                drawLine(g, new Point(x1, y1), new Point(x2, y2), UIColor.BRIGHT_THEME.getColor());
            }
        }
    }

    private void drawLine(Graphics g, Point p1, Point p2, Color color) {
        Color colorBefore = g.getColor();
        g.setColor(color);
        g.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        g.setColor(colorBefore);
    }
}
