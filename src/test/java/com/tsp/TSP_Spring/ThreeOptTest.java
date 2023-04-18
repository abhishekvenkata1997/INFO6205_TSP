package com.tsp.TSP_Spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThreeOptTest {

    @Test
    void testThreeOpt() {
        double[][] distances = {
                { 0, 1, 2, 3, 4 },
                { 1, 0, 1, 4, 3 },
                { 2, 8, 0, 1, 2 },
                { 3, 2, 1, 0, 5 },
                { 4, 3, 2, 1, 0 }
        };
        List<Integer> tour = Arrays.asList(0, 1, 3, 4, 2);
        List<Integer> expected = Arrays.asList(1, 0, 2, 4, 3);
        List<Integer> actual = ThreeOpt.threeOptAlgorithm(tour, distances);
        double actualDistance = TourDistance.tourDistance(actual, distances);
        System.out.print(actual);

        assertEquals(8.0, actualDistance);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void testThreeOptWithLargerInputs() {

        double[][] distances = {
                { 0, 1, 2, 3, 4, 5, 6 },
                { 1, 0, 1, 2, 3, 4, 5 },
                { 2, 1, 0, 1, 2, 3, 4 },
                { 3, 2, 1, 0, 1, 2, 3 },
                { 4, 3, 2, 1, 0, 1, 2 },
                { 5, 4, 3, 2, 1, 0, 1 },
                { 6, 5, 4, 3, 2, 1, 0 }
        };
        List<Integer> tour = Arrays.asList(3, 1, 2, 4, 0, 5, 6);
        List<Integer> expected = Arrays.asList(2, 0, 1, 3, 4, 5, 6);
        List<Integer> actual = ThreeOpt.threeOptAlgorithm(tour, distances);
        System.out.print(actual);
        double actualDistance = TourDistance.tourDistance(actual, distances);
        assertArrayEquals(expected.toArray(), actual.toArray());
        assertEquals(12.0, actualDistance);
    }

    @Test
    void testThreeOptEmptyTour() {
        double[][] distances = {
                { 0, 10, 15, 20 },
                { 10, 0, 35, 25 },
                { 15, 35, 0, 30 },
                { 20, 25, 30, 0 }
        };
        List<Integer> tour = new ArrayList<>(0);
        List<Integer> expected = new ArrayList<>();
        List<Integer> actual = ThreeOpt.threeOptAlgorithm(tour, distances);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

}
