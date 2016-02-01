package com.xkzielinski.points.business;

import com.xkzielinski.points.model.Point;
import com.xkzielinski.points.compare.CompareStrategy;

/**
 * Simple implementation of compare processor - optimized for min distance calculation.
 */
public class MinDistanceProcessor extends DistanceProcessor {

    public MinDistanceProcessor(Point referencePoint, int outputSize, CompareStrategy compareStrategy) {
        super(referencePoint, outputSize, compareStrategy);
    }

    /**
     * @return true if p1 is closer to referencePoint than p2
     */
    @Override
    protected boolean compareToRefPoint(Point p1, Point p2) {
        return getCompareStrategy().compare(getReferencePoint(), p1, p2) < 0;
    }

}
