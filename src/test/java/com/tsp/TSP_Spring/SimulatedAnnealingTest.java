package com.tsp.TSP_Spring;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimulatedAnnealingTest {

    @Test
    void testSimulatedAnnealing() {
        double[][] distances = {
                { 0, 1, 2, 3, 4 },
                { 1, 0, 1, 4, 3 },
                { 2, 8, 0, 1, 2 },
                { 3, 2, 1, 0, 5 },
                { 4, 3, 2, 1, 0 }
        };
        List<Integer> tour = Arrays.asList(0, 1, 3, 4, 2);
        SimulatedAnnealing solver = new SimulatedAnnealing(distances, tour);
        List<Integer> actual = solver.solve(80000000, 1000000, 0.98);
        double actualDistance = TourDistance.tourDistance(actual, distances);
        assertEquals(8.0, actualDistance);

    }

    @Test
    void testSimulatedAnnealingLargerInputs() {
        double[][] distances = {
                { 0, 1, 2, 3, 4, 5, 6 },
                { 1, 0, 1, 2, 3, 4, 5 },
                { 2, 1, 0, 1, 2, 3, 4 },
                { 3, 2, 1, 0, 1, 2, 3 },
                { 4, 3, 2, 1, 0, 1, 2 },
                { 5, 4, 3, 2, 1, 0, 1 },
                { 6, 5, 4, 3, 2, 1, 0 }
        };
        List<Integer> tour = Arrays.asList(0, 1, 3, 4, 2, 5, 6);
        SimulatedAnnealing solver = new SimulatedAnnealing(distances, tour);
        List<Integer> actual = solver.solve(80000000, 1000000, 0.98);
        double actualDistance = TourDistance.tourDistance(actual, distances);
        assertEquals(12.0, actualDistance);

    }

}
