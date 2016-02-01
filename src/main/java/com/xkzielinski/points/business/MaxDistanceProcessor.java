package com.xkzielinski.points.business;

import com.xkzielinski.points.model.Point;
import com.xkzielinski.points.compare.CompareStrategy;

/**
 * Simple implementation of compare processor - optimized for max distance calculation.
 */
public class MaxDistanceProcessor extends DistanceProcessor {

    public MaxDistanceProcessor(Point referencePoint, int outputSize, CompareStrategy compareStrategy) {
        super(referencePoint, outputSize, compareStrategy);
    }

    /**
     * @return true if p1 is further from referencePoint than p2
     */
    @Override
    protected boolean compareToRefPoint(Point p1, Point p2) {
        return getCompareStrategy().compare(getReferencePoint(), p1, p2) > 0;
    }

}
