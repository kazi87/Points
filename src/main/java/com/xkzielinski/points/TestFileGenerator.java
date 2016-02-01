package com.xkzielinski.points;

import com.xkzielinski.points.model.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

/**
 * Simple test files generator
 */
public class TestFileGenerator {
    public static void main(String[] args) {
        try {
            FileOutputStream fos = new FileOutputStream(new File("c:\\simpleTestPoints_20points_10x_10_10_and_10x_20_20"));
            List<Point> points = new LinkedList<>();
            for (int i = 0; i < 20; i++) {
                if (i < 10) {
                    points.add(new Point(10, 10));
                } else {
                    points.add(new Point(20, 20));
                }
            }

            for (Point p : points) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(4);
                byteBuffer.putShort((short) p.getX());
                byteBuffer.putShort((short) p.getY());
                fos.write(byteBuffer.array());
            }
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Can not generate a sample file");
        } catch (IOException e) {
            throw new IllegalStateException("Can not write the data to the ouput stream.", e);
        }
    }


}
