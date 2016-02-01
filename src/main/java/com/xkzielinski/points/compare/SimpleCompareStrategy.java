package com.xkzielinski.points.compare;

import com.xkzielinski.points.model.Point;

/**
 * A simple implementation of the CompareStrategy
 */
public class SimpleCompareStrategy implements CompareStrategy {

    @Override
    public int compare(Point referencePoint, Point p1, Point p2) {

        double distance1 = Math.sqrt(Math.pow((referencePoint.getX() - p1.getX()), 2) + Math.pow((referencePoint.getY() - p1.getY()), 2));
        double distance2 = Math.sqrt(Math.pow((referencePoint.getX() - p2.getX()), 2) + Math.pow((referencePoint.getY() - p2.getY()), 2));
        return Double.compare(distance1, distance2);
    }
}
