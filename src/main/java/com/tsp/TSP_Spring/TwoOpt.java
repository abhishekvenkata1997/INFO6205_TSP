package com.tsp.TSP_Spring;

import java.util.Arrays;
import java.util.List;
import java.util.*;
import java.lang.Thread;
import java.util.concurrent.TimeUnit;

import java.util.Arrays;
import java.util.List;
import java.util.*;
import java.lang.Thread;
import java.util.concurrent.TimeUnit;

public class TwoOpt {

    public static int[] twoOpt(int[] tour, double[][] distanceMatrix) {
        int n = tour.length;
        int[] newTour = Arrays.copyOf(tour, n);
        boolean improved = true;

        while (improved) {
            improved = false;
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    int[] reversed = reverse(newTour, i, j);
                    double newLength = TourDistance.calculateTourDistance(distanceMatrix, reversed);
                    if (newLength < TourDistance.calculateTourDistance(distanceMatrix, newTour)) {
                        newTour = reversed;
                        improved = true;
                    }
                }
            }
        }
        return newTour;
    }

    public static int[] reverse(int[] tour, int i, int j) {
        int[] reversed = Arrays.copyOf(tour, tour.length);
        while (i < j) {
            int temp = reversed[i];
            reversed[i] = reversed[j];
            reversed[j] = temp;
            i++;
            j--;
        }
        return reversed;
    }

}
