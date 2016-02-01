package com.xkzielinski.points.model;

import org.junit.Test;

import java.nio.ByteBuffer;

import static org.junit.Assert.*;

/**
 * Tests for the Point#valueOf method
 */
public class PointTest {

    @Test
    public void testValueOf() throws Exception {
        //  given
        short x = 1;
        short y = 2;
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putShort(x);
        buffer.putShort(y);
        //  when
        Point p = Point.valueOf(buffer.array());
        //  then
        assertEquals(x, p.getX());
        assertEquals(y, p.getY());
    }

    @Test
    public void verifyMaxCornerCase() throws Exception {
        //  given
        short x = Short.MAX_VALUE;
        short y = Short.MAX_VALUE;
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putShort(x);
        buffer.putShort(y);
        //  when
        Point p = Point.valueOf(buffer.array());
        //  then
        assertEquals(x, p.getX());
        assertEquals(y, p.getY());
    }

    @Test
    public void verifyMinCornerCase() throws Exception {
        //  given
        short x = Short.MIN_VALUE;
        short y = Short.MIN_VALUE;
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putShort(x);
        buffer.putShort(y);
        //  when
        Point p = Point.valueOf(buffer.array());
        //  then
        assertEquals(x, p.getX());
        assertEquals(y, p.getY());
    }

    @Test
    public void shouldParseNegativeValues() throws Exception {
        //  given
        short x = -11;
        short y = -2;
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putShort(x);
        buffer.putShort(y);
        //  when
        Point p = Point.valueOf(buffer.array());
        //  then
        assertEquals(x, p.getX());
        assertEquals(y, p.getY());
    }

    @Test
    public void shouldParseZeroValues() throws Exception {
        //  given
        short x = 0;
        short y = 0;
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putShort(x);
        buffer.putShort(y);
        //  when
        Point p = Point.valueOf(buffer.array());
        //  then
        assertEquals(x, p.getX());
        assertEquals(y, p.getY());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRiseAnExceptionIfBytesAreInvalid() throws Exception {
        //  given
        short x = 0;
        short y = 0;
        ByteBuffer buffer = ByteBuffer.allocate(5);
        buffer.putShort(x);
        buffer.putShort(y);
        //  when
        Point p = Point.valueOf(buffer.array());
        //  then
        assertEquals(x, p.getX());
        assertEquals(y, p.getY());
    }
}