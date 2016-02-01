package com.xkzielinski.points.compare;

import com.xkzielinski.points.model.Point;

/**
 * A optimize implementation of the CompareStrategy, uses long values for calculation
 */
public class IntegerCompareStrategy implements CompareStrategy {

    /**
     * To decide which distance is smaller, we can skip some expensive operations (e.g. Math.sqr).
     * Thanks that, we can as well replace a double by the int (to avoid operations on real numbers).
     *
     * @param referencePoint - the reference point (we compare the distance from the p1 to the referencePoint and from the p2 to the referencePoint)
     * @param p1             - first point to compare
     * @param p2             - second point to compare
     * @return
     */
    @Override
    public int compare(Point referencePoint, Point p1, Point p2) {
        long distance1 = getDistance(referencePoint, p1);
        long distance2 = getDistance(referencePoint, p2);

        return Long.compare(distance1, distance2);
    }

    protected long getDistance(Point referencePoint, Point p1) {
        long p1x = (referencePoint.getX() - p1.getX()) * (referencePoint.getX() - p1.getX());
        long p1y = (referencePoint.getY() - p1.getY()) * (referencePoint.getY() - p1.getY());
        return p1x + p1y;
    }
}
