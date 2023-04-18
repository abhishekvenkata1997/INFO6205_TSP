package com.tsp.TSP_Spring;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AntColonyTest {

    @Test
    void testAntColony() {
        double[][] distances = {
                { 0, 1, 2, 3, 4 },
                { 1, 0, 1, 4, 3 },
                { 2, 8, 0, 1, 2 },
                { 3, 2, 1, 0, 5 },
                { 4, 3, 2, 1, 0 }
        };
        int[] expected = { 4, 3, 2, 0, 1 };

        ACO aco = new ACO(distances.length, distances, 3.0, 6.0, 0.1, 0.15, 23, 12345L);
        aco.solve(100);

        int[] actual = aco.getBestTour();
        double actualDistance = TourDistance.calculateTourDistance(distances, actual);

        // end code logic

        for (int i = 0; i < actual.length; i++) {
            System.out.print(actual[i] + " ");
        }
        assertEquals(8.0, actualDistance);
        assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    void testAntColonyLargerInput() {
        double[][] distances = {
                { 0, 1, 2, 3, 4, 5, 6 },
                { 1, 0, 1, 2, 3, 4, 5 },
                { 2, 1, 0, 1, 2, 3, 4 },
                { 3, 2, 1, 0, 1, 2, 3 },
                { 4, 3, 2, 1, 0, 1, 2 },
                { 5, 4, 3, 2, 1, 0, 1 },
                { 6, 5, 4, 3, 2, 1, 0 }
        };
        int[] expected = { 5, 6, 4, 3, 2, 1, 0 };

        ACO aco = new ACO(distances.length, distances, 3.0, 6.0, 0.1, 0.15, 23, 12345L);
        aco.solve(100);

        int[] actual = aco.getBestTour();
        double actualDistance = TourDistance.calculateTourDistance(distances, actual);

        // end code logic

        for (int i = 0; i < actual.length; i++) {
            System.out.print(actual[i] + " ");
        }
        assertEquals(12.0, actualDistance);
        assertTrue(Arrays.equals(expected, actual));
    }
}
