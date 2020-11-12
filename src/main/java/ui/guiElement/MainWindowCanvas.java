package ui.guiElement;

import entity.ApplicationContext;
import entity.ColorPoint;
import util.Pair;
import entity.Point;
import entity.Point2D;
import enums.UIColor;
import enums.UIDimensionParameter;

import java.awt.*;
import java.util.Collection;

public class MainWindowCanvas extends Canvas {

    private final MainCanvasUtils mainCanvasUtils = new MainCanvasUtils();
    private final ApplicationContext applicationContext;

    private MainWindowCanvas(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
        setBackground(UIColor.BACKGROUND.getColor());
        setForeground(UIColor.FOREGROUND.getColor());
        setSize(UIDimensionParameter.WIDTH.getValue(),
                Math.min(UIDimensionParameter.WIDTH.getValue(), UIDimensionParameter.HEIGHT.getValue()));
    }

    public static MainWindowCanvas getInstance(ApplicationContext applicationContext){
        return new MainWindowCanvas(applicationContext);
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

    @Override
    public void paint(Graphics g) {
        drawDots(g, applicationContext.getPoints());
//        drawAllLines(g);
        drawLines(g, applicationContext.getLines());
    }

    private void drawDots(Graphics g, Collection<ColorPoint> colorPoints) {
        Color colorBefore = g.getColor();
        for (ColorPoint colorPoint : colorPoints) {
            g.setColor(colorPoint.getColor());
            Point point = mainCanvasUtils.scaledPointOf(colorPoint.getPoint());
            g.fillOval(point.getX(), point.getY(), mainCanvasUtils.getDotWidth(), mainCanvasUtils.getDotWidth());
        }
        g.setColor(colorBefore);
    }

    private void drawLines(Graphics g, Collection<? extends Pair<? extends Point2D>> lineDefinitionPoints){
        int shift = mainCanvasUtils.getDotWidth() / 2;
        for(Pair<? extends Point2D> point2DPair: lineDefinitionPoints){
            int x1 = mainCanvasUtils.scaledPointOf(point2DPair.getFirst()).getX() + shift;
            int y1 = mainCanvasUtils.scaledPointOf(point2DPair.getFirst()).getY() + shift;
            int x2 = mainCanvasUtils.scaledPointOf(point2DPair.getSecond()).getX() + shift;
            int y2 = mainCanvasUtils.scaledPointOf(point2DPair.getSecond()).getY() + shift;
            drawLine(g, new Point(x1, y1), new Point(x2, y2), UIColor.GREEN_THEME_LINE.getColor());
        }
    }

    private void drawAllLines(Graphics g, Collection<ColorPoint> colorPoints) {
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
