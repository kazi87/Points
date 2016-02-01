package com.xkzielinski.points.compare;

import com.xkzielinski.points.model.Point;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The extension class of the IntegerStrategy - store a computation result in the buffer.
 * Comment:  Based on the tests, this implementation is not faster than normal IntegerCompareStrategy for a given input!
 */
public class OptimizedCompareStrategy extends IntegerCompareStrategy{

    //  keep 100 last processed points
    private final LinkedHashMap<String, Long> lastProceedMap;
    private final int bufferSize = 100;

    public OptimizedCompareStrategy() {
        //  a simple implementation of FIFO Map.
        lastProceedMap = new LinkedHashMap<String, Long>(bufferSize){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() >= bufferSize;
            }
        };
    }

    @Override
    protected long getDistance(Point referencePoint, Point p1) {
        Long distance = getFromMap(p1.toString());
        if(distance == null){
            distance = super.getDistance(referencePoint, p1);
            putToMap(p1, distance);
        }
        return distance;
    }

    private Long getFromMap(String key){
        return lastProceedMap.get(key);
    }

    private Long putToMap(Point p, Long distance){
        return lastProceedMap.put(p.toString(), distance);
    }
}
