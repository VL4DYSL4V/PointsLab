package util;

import entity.Point;
import entity.Point2D;

public final class GeometryUtil {

    public static final Point2D I = new Point(1, 0);
    public static final Point2D J = new Point(0, 1);

    public static int compareToLine(Point2D linePoint1, Point2D linePoint2, Point2D anyPoint) {
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

    public static double getAngle(Point2D vector1, Point2D vector2) {
        double divisor = getVectorLength(vector1) * getVectorLength(vector2);
        double res = Math.acos(
                dotProduct(vector1, vector2) / divisor);
        return Math.toDegrees(res);
    }

    private static int dotProduct(Point2D vector1, Point2D vector2) {
        return vector1.getX() * vector2.getX() + vector1.getY() * vector2.getY();
    }

    private static double getVectorLength(Point2D vector) {
        return Math.sqrt(Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2));
    }
}
