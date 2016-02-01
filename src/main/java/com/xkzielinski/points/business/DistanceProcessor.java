package com.xkzielinski.points.business;

import com.xkzielinski.points.model.Point;
import com.xkzielinski.points.compare.CompareStrategy;

import java.util.LinkedList;
import java.util.List;

/**
 * Common class for the CompareProcessors
 */
public abstract class DistanceProcessor {

    private final int outputExpectedSize;
    private final Point referencePoint;
    private final List<Point> result;
    private final CompareStrategy compareStrategy;

    public DistanceProcessor(Point referencePoint, int outputExpectedSize, CompareStrategy compareStrategy) {
        if(referencePoint == null){
            throw new IllegalArgumentException("ReferencePoint can not be null");
        }
        if(outputExpectedSize <= 0){
            throw new IllegalArgumentException("Output size has to be a positive number");
        }
        this.referencePoint = referencePoint;
        this.outputExpectedSize = outputExpectedSize;
        this.compareStrategy = compareStrategy;
        result = new LinkedList<>();
    }

    /**
     * Shortcut method for the CompareStrategy@compare
     */
    protected abstract boolean compareToRefPoint(Point p1, Point p2);


    /**
     * Process the point.
     * If there is a capacity - adds point to the result list.
     * If there is no capacity, compare to the border point and based on the result add or not to the result set
     */
    public void processPoint(Point point) {
        if(capacity() > 0){
            addResultPoint(point);
        }else {
            if (compareToRefPoint(point, getBorderPoint())) {
                addResultPoint(point);
            }
        }
    }

    private void addResultPoint(Point p) {
        if(capacity() == 0) {
            getResult().remove(0);
        }
        //  bubble up the new point
        for(int i = 0 ; i < getResult().size() ; i++){
            Point nextPoint = getResult().get(i);
            if(compareToRefPoint(nextPoint, p)){
                getResult().add(i, p);
                return;
            }
        }
        getResult().add(p);
    }

    /**
     * @return the border point from the already proceed point collection.
     */
    public Point getBorderPoint() {
        return getResult().isEmpty() ? null : getResult().get(0);
    }


    final public List<Point> getResult() {
        return result;
    }

    final public int capacity() {
        return outputExpectedSize - result.size();
    }

    final public int size() {
        return result.size();
    }

    public Point getReferencePoint() {
        return referencePoint;
    }

    public CompareStrategy getCompareStrategy() {
        return compareStrategy;
    }
}
