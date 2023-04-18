package com.tsp.TSP_Spring;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TwoOptTest {

        @Test
        void testTwoOpt() {
                double[][] distances = {
                                { 0, 1, 2, 3, 4 },
                                { 1, 0, 1, 4, 3 },
                                { 2, 8, 0, 1, 2 },
                                { 3, 2, 1, 0, 5 },
                                { 4, 3, 2, 1, 0 }
                };
                int[] tour = { 0, 1, 3, 4, 2 };
                int[] expected = { 3, 1, 0, 2, 4 };
                int[] actual = TwoOpt.twoOpt(tour, distances);
                double actualDistance = TourDistance.calculateTourDistance(distances, actual);
                assertEquals(8.0, actualDistance);
                assertTrue(Arrays.equals(expected, actual));
        }

        @Test
        void testTwoOptWithLargerInputs() {

                double[][] distances = {
                                { 0, 1, 2, 3, 4, 5, 6 },
                                { 1, 0, 1, 2, 3, 4, 5 },
                                { 2, 1, 0, 1, 2, 3, 4 },
                                { 3, 2, 1, 0, 1, 2, 3 },
                                { 4, 3, 2, 1, 0, 1, 2 },
                                { 5, 4, 3, 2, 1, 0, 1 },
                                { 6, 5, 4, 3, 2, 1, 0 }
                };
                int[] tour = { 3, 1, 2, 4, 0, 5, 6 };
                int[] expected = { 4, 3, 2, 1, 0, 5, 6 };
                int[] actual = TwoOpt.twoOpt(tour, distances);
                double actualDistance = TourDistance.calculateTourDistance(distances, actual);
                assertTrue(Arrays.equals(expected, actual));
                assertEquals(12.0, actualDistance);
        }

}
