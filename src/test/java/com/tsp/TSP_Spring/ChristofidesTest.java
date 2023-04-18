package com.tsp.TSP_Spring;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChristofidesTest {

    @Test
    void testChristofides() {
        double[][] distances = {
                { 0, 1, 2, 3, 4 },
                { 1, 0, 1, 4, 3 },
                { 2, 8, 0, 1, 2 },
                { 3, 2, 1, 0, 5 },
                { 4, 3, 2, 1, 0 }
        };
        int[] tour = { 0, 1, 3, 4, 2 };
        int[] expected = { 0, 1, 2, 3, 4 };

        int[] actual = Christofides.applyChristofidesAlgorithm(distances);
        double actualDistance = TourDistance.calculateTourDistance(distances, actual);

        assertEquals(12.0, actualDistance);
        assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    void testChristofidesLargerInput() {

        double[][] distances = {
                { 0, 1, 2, 3, 4, 5, 6 },
                { 1, 0, 1, 2, 3, 4, 5 },
                { 2, 1, 0, 1, 2, 3, 4 },
                { 3, 2, 1, 0, 1, 2, 3 },
                { 4, 3, 2, 1, 0, 1, 2 },
                { 5, 4, 3, 2, 1, 0, 1 },
                { 6, 5, 4, 3, 2, 1, 0 }
        };
        int[] expected = { 0, 1, 2, 3, 4, 5, 6 };
        int[] actual = Christofides.applyChristofidesAlgorithm(distances);
        double actualDistance = TourDistance.calculateTourDistance(distances, actual);

        assertTrue(Arrays.equals(expected, actual));
        assertEquals(12.0, actualDistance);
    }

    @Test
    void testChristofidesTour() {
        double[][] distances = {
                { 0, 10, 15, 20 },
                { 10, 0, 35, 25 },
                { 15, 35, 0, 30 },
                { 20, 25, 30, 0 }
        };
        int[] expected = { 0, 1, 3, 2 };
        int[] actual = Christofides.applyChristofidesAlgorithm(distances);
        double actualDistance = TourDistance.calculateTourDistance(distances, actual);
        assertTrue(Arrays.equals(expected, actual));
        assertEquals(80.0, actualDistance);
    }
}
