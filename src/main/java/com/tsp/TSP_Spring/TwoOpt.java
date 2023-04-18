package com.tsp.TSP_Spring;

import java.util.Arrays;

public class TwoOpt {

    public static int[] twoOpt(int[] tour, double[][] distanceMatrix) {
        int numCities = tour.length;
        int[] newTour = Arrays.copyOf(tour, numCities);
        boolean tourImproved = true;

        while (tourImproved) {
            tourImproved = false;
            for (int i = 0; i < numCities - 1; i++) {
                for (int j = i + 1; j < numCities; j++) {
                    int[] reversedTour = reverseTour(newTour, i, j);
                    double newTourLength = TourDistance.calculateTourDistance(distanceMatrix, reversedTour);
                    double currentTourLength = TourDistance.calculateTourDistance(distanceMatrix, newTour);
                    if (newTourLength < currentTourLength) {
                        newTour = reversedTour;
                        tourImproved = true;
                    }
                }
            }
        }
        return newTour;
    }

    public static int[] reverseTour(int[] tour, int startIndex, int endIndex) {
        int[] reversedTour = Arrays.copyOf(tour, tour.length);
        while (startIndex < endIndex) {
            int temp = reversedTour[startIndex];
            reversedTour[startIndex] = reversedTour[endIndex];
            reversedTour[endIndex] = temp;
            startIndex++;
            endIndex--;
        }
        return reversedTour;
    }

}
