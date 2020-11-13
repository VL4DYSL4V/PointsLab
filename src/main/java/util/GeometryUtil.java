package util;

import entity.Point;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class GeometryUtil {

    public static final Point I = new Point(1, 0);
    public static final Point J = new Point(0, 1);

    private GeometryUtil(){}

    public static double getTriangleSquare(Point a, Point b, Point c){
        int a11 = a.getX() - c.getX();
        int a12 = a.getY() - c.getY();
        int a21 = b.getX() - c.getX();
        int a22 = b.getY() - c.getY();
        int det = a11 * a22 - a12 * a21;
        return Math.abs(((double) det )/ 2);
    }

    public static int compareToLine(Point linePoint1, Point linePoint2, Point anyPoint) {
        int coeffA = linePoint2.getY() - linePoint1.getY();
        int coeffB = linePoint1.getX() - linePoint2.getX();
        int coeffC = linePoint2.getX() * linePoint1.getY() -
                linePoint1.getX() * linePoint2.getY();
        double value = coeffA * anyPoint.getX() + coeffB * anyPoint.getY() + coeffC;
        if(value == 0){
            return 0;
        }else if(value < 0){
            return -1;
        }else{
            return 1;
        }
    }
//
//    public static double getDistance(Point2D linePoint1, Point2D linePoint2, Point2D anyPoint) {
//        int coeffA = linePoint2.getY() - linePoint1.getY();
//        int coeffB = linePoint1.getX() - linePoint2.getX();
//        int coeffC = linePoint2.getX() * linePoint1.getY() -
//                linePoint1.getX() * linePoint2.getY();
//        double muValue = Math.sqrt(Math.pow(coeffA, 2) + Math.pow(coeffB, 2));
//        double mu = (coeffC <= 0) ? muValue : (-1) * muValue;
//        return (coeffA * anyPoint.getX() + coeffB * anyPoint.getY() + coeffC) / mu;
//    }



    public static List<Point> getUpperAndEqualSubset(Point leftLineDefinitionPoint, Point RightDefinitionPoint, Collection<Point> points){
        return points.stream().filter(p ->
                compareToLine(leftLineDefinitionPoint, RightDefinitionPoint, p) >= 0).collect(Collectors.toList());
    }

    public static List<Point> getLowerAndEqualSubset(Point leftLineDefinitionPoint, Point RightDefinitionPoint, Collection<Point> points){
        return points.stream().filter(p ->
                compareToLine(leftLineDefinitionPoint, RightDefinitionPoint, p) <= 0).collect(Collectors.toList());
    }

    public static double getAngle(Point vector1, Point vector2) {
        double divisor = getVectorLength(vector1) * getVectorLength(vector2);
        double res = Math.acos(
                dotProduct(vector1, vector2) / divisor);
        return Math.toDegrees(res);
    }

    private static int dotProduct(Point vector1, Point vector2) {
        return vector1.getX() * vector2.getX() + vector1.getY() * vector2.getY();
    }

    private static double getVectorLength(Point vector) {
        return Math.sqrt(Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2));
    }
}
