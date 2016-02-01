package com.xkzielinski.points;

import com.xkzielinski.points.business.DistanceProcessor;
import com.xkzielinski.points.business.MinDistanceProcessor;
import com.xkzielinski.points.compare.CompareStrategy;
import com.xkzielinski.points.compare.IntegerCompareStrategy;
import com.xkzielinski.points.model.Point;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Simple tests with input files.
 */
public class StreamProcessorTest {

    private int outputSize;
    private CompareStrategy compareStrategy;

    private StreamProcessor testObj;

    @Before
    public void setUp() {
        outputSize = 10;
        compareStrategy = new IntegerCompareStrategy();
    }

    @Test
    public void shouldFind10TheClosestPointsTo_20_20() throws Exception {
        //  given
        DistanceProcessor distanceProcessor = new MinDistanceProcessor(new Point(20, 20), outputSize, compareStrategy);
        testObj = new StreamProcessor(distanceProcessor);
        InputStream is = this.getClass().getResourceAsStream("/simpleTestPoints_100points_00_to_99");
        //  when
        testObj.process(is);
        List<Point> result = distanceProcessor.getResult();
        //  then
        assertEquals(10, result.size());

        Point closest = result.get(9);
        assertEquals(20, closest.getX());
        assertEquals(20, closest.getY());
        Point furthest = result.get(0);
        assertEquals(15, furthest.getX());
        assertEquals(15, furthest.getY());
    }

    @Test
    public void shouldFind10TheClosestPointsToNegative_100_100() throws Exception {
        //  given
        DistanceProcessor distanceProcessor = new MinDistanceProcessor(new Point(-100, -100), outputSize, compareStrategy);
        testObj = new StreamProcessor(distanceProcessor);
        InputStream is = this.getClass().getResourceAsStream("/simpleTestPoints_100points_00_to_99");
        //  when
        testObj.process(is);
        List<Point> result = distanceProcessor.getResult();
        //  then
        assertEquals(10, result.size());

        Point closest = result.get(9);
        assertEquals(0, closest.getX());
        assertEquals(0, closest.getY());
        Point furthest = result.get(0);
        assertEquals(9, furthest.getX());
        assertEquals(9, furthest.getY());
    }

    @Test
    public void shouldFind10Points10_10() throws Exception {
        //  given
        DistanceProcessor distanceProcessor = new MinDistanceProcessor(new Point(10, 10), outputSize, compareStrategy);
        testObj = new StreamProcessor(distanceProcessor);
        InputStream is = this.getClass().getResourceAsStream("/simpleTestPoints_20points_10x_10_10_and_10x_20_20");
        //  when
        testObj.process(is);
        List<Point> result = distanceProcessor.getResult();
        //  then
        assertEquals(10, result.size());

        Point closest = result.get(9);
        assertEquals(10, closest.getX());
        assertEquals(10, closest.getY());
        Point furthest = result.get(0);
        assertEquals(10, furthest.getX());
        assertEquals(10, furthest.getY());
    }

    @Test
    public void shouldFind10Points_10_10_andOne20_20() throws Exception {
        //  given
        int outputSize = 11;
        DistanceProcessor distanceProcessor = new MinDistanceProcessor(new Point(10, 10), outputSize, compareStrategy);
        testObj = new StreamProcessor(distanceProcessor);
        InputStream is = this.getClass().getResourceAsStream("/simpleTestPoints_20points_10x_10_10_and_10x_20_20");
        //  when
        testObj.process(is);
        List<Point> result = distanceProcessor.getResult();
        //  then
        assertEquals(11, result.size());

        Point closest = result.get(9);
        assertEquals(10, closest.getX());
        assertEquals(10, closest.getY());
        Point furthest = result.get(0);
        assertEquals(20, furthest.getX());
        assertEquals(20, furthest.getY());
    }
}