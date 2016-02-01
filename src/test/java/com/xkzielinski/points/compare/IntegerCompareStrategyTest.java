package com.xkzielinski.points.compare;


import com.xkzielinski.points.model.Point;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IntegerCompareStrategyTest {

    private final Point p1;
    private final Point p2;
    private final int expected;

    private CompareStrategy compareStrategy;
    private Point referencePoint;

    public IntegerCompareStrategyTest(int expected, Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.expected = expected;
    }

    @Before
    public void setUp() {
        referencePoint = new Point(0, 0);
        compareStrategy = new IntegerCompareStrategy();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][]{
                        {0, new Point(0, 0), new Point(0, 0)},
                        {0, new Point(-1, -1), new Point(1, 1)},
                        {0, new Point(1, 1), new Point(1, 1)},
                        {0, new Point(Short.MAX_VALUE, Short.MAX_VALUE), new Point(Short.MAX_VALUE, Short.MAX_VALUE)},
                        {-1, new Point(1, 1), new Point(2, 2)},
                        {-1, new Point(1, 1), new Point(Short.MAX_VALUE, 2)},
                        {-1, new Point(Short.MAX_VALUE, Short.MAX_VALUE), new Point(Short.MIN_VALUE, Short.MIN_VALUE)},
                        {1, new Point(2, 2), new Point(1, 1)},
                });
    }

    @Test
    public void test() {
        assertEquals(expected, compareStrategy.compare(referencePoint, p1, p2));
    }

}