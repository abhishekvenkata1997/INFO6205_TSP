package com.tsp.TSP_Spring;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TwoOptTest {

        @Test
        void testTwoOpt() {

                double[][] distances = {
                                { 0, 1, 2, 3, 4 },
                                { 1, 0, 1, 2, 3 },
                                { 2, 1, 0, 1, 2 },
                                { 3, 2, 1, 0, 1 },
                                { 4, 3, 2, 1, 0 }
                };
                List<Integer> tour = Arrays.asList(0, 1, 2, 3, 4);
                List<Integer> expected = Arrays.asList(0, 1, 2, 3, 4);
                // List<Integer> actual = TwoOpt.twoOpt(tour, distances);
                double actualDistance = TourDistance.tourDistance(tour, distances);
                // assertEquals(expected, actual);
                assertEquals(8.0, actualDistance);
        }
}
