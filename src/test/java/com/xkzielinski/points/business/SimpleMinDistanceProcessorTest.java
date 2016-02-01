package com.xkzielinski.points.business;

import com.xkzielinski.points.model.Point;
import com.xkzielinski.points.compare.CompareStrategy;
import com.xkzielinski.points.compare.SimpleCompareStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnits for MinDistanceProcessor with SimpleCompareStrategy
 */
public class SimpleMinDistanceProcessorTest {

    /**
     * Helper class to test the DistanceProcessor.
     * Simulates logic of the MinDistanceProcessor.
     */
    private class TestMinDistanceProcessor extends DistanceProcessor {

        public TestMinDistanceProcessor(Point referencePoint, int outputExpectedSize, CompareStrategy compareStrategy) {
            super(referencePoint, outputExpectedSize, compareStrategy);
        }

        @Override
        protected boolean compareToRefPoint(Point p1, Point p2) {
            return compare(p1, p2) < 0;
        }
    }

    private static Point REFERENCE_POINT = new Point();
    public static final int OUTPUT_SIZE = 5;

    private TestMinDistanceProcessor testObj;
    private CompareStrategy compareStrategy;

    @Before
    public void setUp(){
        compareStrategy = new SimpleCompareStrategy();
        testObj = new TestMinDistanceProcessor(REFERENCE_POINT, OUTPUT_SIZE, compareStrategy);
    }

    @Test
    public void shouldAddSinglePoint() throws Exception {
        //  given
        Point p = new Point(1, 2);
        //  when
        testObj.processPoint(p);
        //  then
        assertEquals(1, testObj.size());
    }

    @Test
    public void shouldAddPointWithNegativeCoordinates() throws Exception {
        //  given
        Point p = new Point(-1, -2);
        //  when
        testObj.processPoint(p);
        //  then
        assertEquals(-1, testObj.getResult().get(0).getX());
        assertEquals(-2, testObj.getResult().get(0).getY());
    }

    @Test
    public void shouldAddPointWithRefPointCoordinates() throws Exception {
        //  given
        Point p = new Point(0, 0);
        //  when
        testObj.processPoint(p);
        //  then
        assertEquals(0, testObj.getResult().get(0).getX());
        assertEquals(0, testObj.getResult().get(0).getY());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAllowNullReferencePoint() throws Exception {
        //  given
        //  when
        testObj = new TestMinDistanceProcessor(null, 1, null);
        //  then
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAllowNonPositiveCapacity() throws Exception {
        //  given
        //  when
        testObj = new TestMinDistanceProcessor(REFERENCE_POINT, 0, null);
        //  then
    }

    @Test
    public void shouldAddAsManyPointsAsDefinedCapacity() throws Exception {
        //  given
        Point p1 = new Point(1, 2);
        Point p2 = new Point(1, 2);
        Point p3 = new Point(1, 2);
        Point p4 = new Point(1, 2);
        Point p5 = new Point(1, 2);
        //  when
        testObj.processPoint(p1);
        testObj.processPoint(p2);
        testObj.processPoint(p3);
        testObj.processPoint(p4);
        testObj.processPoint(p5);
        //  then
        assertEquals(5, testObj.size());
    }

    @Test
    public void shouldSortTheResultAscending() throws Exception {
        //  given
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 2);
        Point p3 = new Point(3, 3);
        Point p4 = new Point(4, 4);
        Point p5 = new Point(5, 5);
        //  when
        testObj.processPoint(p1);
        testObj.processPoint(p2);
        testObj.processPoint(p3);
        testObj.processPoint(p4);
        testObj.processPoint(p5);

        //  then
        assertTrue(compare(testObj.getResult().get(0), testObj.getResult().get(1)) > 0);
        assertTrue(compare(testObj.getResult().get(1), testObj.getResult().get(2)) > 0);
        assertTrue(compare(testObj.getResult().get(2), testObj.getResult().get(3)) > 0);
        assertTrue(compare(testObj.getResult().get(3), testObj.getResult().get(4)) > 0);
    }

    @Test
    public void shouldSortTheResultAscendingIfDescendingInput() throws Exception {
        //  given
        Point p1 = new Point(5, 5);
        Point p2 = new Point(4, 4);
        Point p3 = new Point(3, 3);
        Point p4 = new Point(2, 2);
        Point p5 = new Point(1, 1);
        //  when
        testObj.processPoint(p1);
        testObj.processPoint(p2);
        testObj.processPoint(p3);
        testObj.processPoint(p4);
        testObj.processPoint(p5);
        //  then
        assertTrue(compare(testObj.getResult().get(0), testObj.getResult().get(1)) > 0);
        assertTrue(compare(testObj.getResult().get(1), testObj.getResult().get(2)) > 0);
        assertTrue(compare(testObj.getResult().get(2), testObj.getResult().get(3)) > 0);
        assertTrue(compare(testObj.getResult().get(3), testObj.getResult().get(4)) > 0);
    }

   @Test
    public void shouldBubbleUp() throws Exception {
        //  given
       testObj = new TestMinDistanceProcessor(REFERENCE_POINT, 2, compareStrategy);
        Point p1 = new Point(-2, -2);
        Point p2 = new Point(-1, -1);
        Point p3 = new Point(0, 0);
        Point p4 = new Point(1, 1);
        Point p5 = new Point(2, 2);
        //  when
        testObj.processPoint(p1);
        testObj.processPoint(p2);
        testObj.processPoint(p3);
        testObj.processPoint(p4);
        testObj.processPoint(p5);
        //  then
        assertEquals(p2, testObj.getResult().get(0));
        assertEquals(p3, testObj.getResult().get(1));

    }

    /**
     * As the ref point is the (0,0), we can simply compare sqr results
     * @return
     */
    private int compare(Point p1, Point p2){
        double p1Distance = Math.sqrt(p1.getX() * p1.getX() + p1.getY() * p1.getY());
        double p2Distance = Math.sqrt(p2.getX() * p2.getX() + p2.getY() * p2.getY());
        return Double.compare(p1Distance, p2Distance);
    }
}