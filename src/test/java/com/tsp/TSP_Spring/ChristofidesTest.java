package com.tsp.TSP_Spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        int[] expected = { 3, 1, 0, 4, 2 };
        int[] actual = Christofides.applyChristofidesAlgorithm(distances, tour);
        double actualDistance = TourDistance.calculateTourDistance(distances, actual);
        for (int i = 0; i < actual.length; i++) {
            System.out.print(actual[i] + " ");
        }
        assertEquals(10.0, actualDistance);
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
        int[] tour = { 3, 1, 2, 4, 0, 5, 6 };
        int[] expected = { 4, 2, 1, 3, 0, 5, 6 };
        int[] actual = Christofides.applyChristofidesAlgorithm(distances, tour);
        double actualDistance = TourDistance.calculateTourDistance(distances, actual);
        for (int i = 0; i < actual.length; i++) {
            System.out.print(actual[i] + " ");
        }
        assertTrue(Arrays.equals(expected, actual));
        assertEquals(16.0, actualDistance);
    }

    @Test
    void testChristofidesTour() {
        double[][] distances = {
                { 0, 10, 15, 20 },
                { 10, 0, 35, 25 },
                { 15, 35, 0, 30 },
                { 20, 25, 30, 0 }
        };
        int[] tour = { 1, 2, 3, 0 };
        int[] expected = { 3, 2, 1, 0 };
        int[] actual = Christofides.applyChristofidesAlgorithm(distances, tour);
        double actualDistance = TourDistance.calculateTourDistance(distances, actual);
        for (int i = 0; i < actual.length; i++) {
            System.out.print(actual[i] + " ");
        }
        assertTrue(Arrays.equals(expected, actual));
        assertEquals(95.0, actualDistance);
    }
}
