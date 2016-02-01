package com.xkzielinski.points.compare;

import com.xkzielinski.points.model.Point;

/**
 * The interface for the distance comparison.
 */
public interface CompareStrategy {

    /**
     * Compare distances between points p1/p2 and reference point (initialized in constructor)
     * @param referencePoint - the reference point (we compare the distance from the p1 to the referencePoint and from the p2 to the referencePoint)
     * @param p1 - first point to compare
     * @param p2 - second point to compare

     * @return 0 if the distance from p1 to the referencePoint is equal to the distance from p2 to the referencePoint.
     * value < 0 if the distance from p1 to the referencePoint is smaller than the distance from p2 to the referencePoint
     * value > 0 if the distance from p1 to the referencePoint is greater than the distance from p2 to the referencePoint
     */
    int compare(Point referencePoint, Point p1, Point p2);
}
