package com.xkzielinski.points;

import com.xkzielinski.points.business.DistanceProcessor;
import com.xkzielinski.points.model.Point;

import java.io.IOException;
import java.io.InputStream;

/**
 * Processes the stream and calculate the result list
 */
public class StreamProcessor {

    private final DistanceProcessor[] distanceProcessor;

    public StreamProcessor(DistanceProcessor... distanceProcessor) {
        if (distanceProcessor == null || distanceProcessor.length == 0) {
            throw new IllegalArgumentException("DistanceProcessor can not be null");
        }
        this.distanceProcessor = distanceProcessor;
    }

    public void process(InputStream inputStream) {
        if (inputStream == null) {
            throw new IllegalArgumentException("The input stream can not be null.");
        }
        processValidStream(inputStream);
    }

    /**
     * Process notnull input stream.
     */
    private void processValidStream(InputStream inputStream) {
        byte[] buffer = new byte[4];
        try {
            while (inputStream.read(buffer) > -1) {
                executeProcessors(buffer);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Can not read data from the stream!", e);
        }
    }

    /**
     * Parse the byte array to point, and executes all processors
     *
     * @param buffer
     */
    private void executeProcessors(byte[] buffer) {
        Point point = Point.valueOf(buffer);
        for (DistanceProcessor processor : distanceProcessor) {
            processor.processPoint(point);
        }
    }

}
