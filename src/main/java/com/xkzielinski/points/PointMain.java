package com.xkzielinski.points;

import com.xkzielinski.points.business.DistanceProcessor;
import com.xkzielinski.points.business.MaxDistanceProcessor;
import com.xkzielinski.points.business.MinDistanceProcessor;
import com.xkzielinski.points.compare.CompareStrategy;
import com.xkzielinski.points.compare.IntegerCompareStrategy;
import com.xkzielinski.points.model.Point;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * The main class for the point processor
 */
public class PointMain {

    public static void main(String args[]) {
        InputStream inputStream = parseArguments(args);

        //  test case 1 (100, -300), output: the 2 closest points
        CompareStrategy compareStrategy = new IntegerCompareStrategy();
        DistanceProcessor minDistanceProcessor = new MinDistanceProcessor(new Point(-200, 300), 2, compareStrategy);

        //  test case 2 (111, 25), output: the 11 furthest points
        CompareStrategy maxCompareStrategy = new IntegerCompareStrategy();
        DistanceProcessor maxDistanceProcessor = new MaxDistanceProcessor(new Point(1000, 25), 11, maxCompareStrategy);

        long startTIme = System.currentTimeMillis();
        try {
            System.out.println("Start processing...");
            StreamProcessor streamProcessor = new StreamProcessor(minDistanceProcessor, maxDistanceProcessor);
            streamProcessor.process(inputStream);
            System.out.println("Done");
            System.out.println("RESULT for testCase1: " + minDistanceProcessor.getResult());
            System.out.println("RESULT for testCase2: " + maxDistanceProcessor.getResult());
        } catch (Exception e) {
            throw new IllegalStateException("Error while processing the input data", e);
        } finally {
            System.out.println("Total processTime: " + (System.currentTimeMillis() - startTIme));
        }
    }

    private static InputStream parseArguments(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("The point file path was not defined. Please define it as a first Java argument.");
        }
        String filePath = args[0];
        try {
            return new FileInputStream(new File(filePath));
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Given file does not exist: " + filePath);
        }
    }
}
