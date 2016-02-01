package com.xkzielinski.points.model;

import java.nio.ByteBuffer;

/**
 * Simple representation of the point.
 */
public class Point {
    private final int x;
    private final int y;

    public static Point valueOf(byte[] bytes){
        if(bytes.length != 4){
            throw new IllegalArgumentException("Invalid input - required short type");
        }
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        int x = byteBuffer.getShort();
        int y = byteBuffer.getShort();
        return new Point(x, y);
    }


    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
