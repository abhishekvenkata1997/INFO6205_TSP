package com.tsp.TSP_Spring;

import java.util.ArrayList;
import java.util.List;

public class ThreeOpt {

    // Swap two edges in a tour and return the resulting tour
    private static List<Integer> swapEdges(List<Integer> tour, int i, int j, int k) {
        List<Integer> newTour = new ArrayList<>();
        for (int x = 0; x <= i - 1; x++) {
            newTour.add(tour.get(x));
        }
        for (int x = j; x >= i; x--) {
            newTour.add(tour.get(x));
        }
        for (int x = k; x >= j + 1; x--) {
            newTour.add(tour.get(x));
        }
        for (int x = k + 1; x < tour.size(); x++) {
            newTour.add(tour.get(x));
        }
        return newTour;
    }

    // Calculate the distance between two cities using the Haversine formula
    private static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double radius = 6371; // Radius of the earth in km
        double latitudeDifference = Math.toRadians(lat2 - lat1);
        double longitudeDifference = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latitudeDifference / 2) * Math.sin(latitudeDifference / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(longitudeDifference / 2) * Math.sin(longitudeDifference / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return radius * c;
    }

    // Calculate the total distance of a tour
    public static double calculateTourDistance(List<Integer> tour, double[][] distances) {
        double distance = 0;
        for (int i = 0; i < tour.size() - 1; i++) {
            distance += distances[tour.get(i)][tour.get(i + 1)];
        }
        distance += distances[tour.get(tour.size() - 1)][tour.get(0)];
        return distance;
    }

    // Run the 3-opt algorithm on a tour and return the optimized tour
    public static List<Integer> threeOptAlgorithm(List<Integer> tour, double[][] distances) {
        int size = tour.size();
        boolean improved = true;
        int iterations = 0;
        while (improved) {
            improved = false;
            iterations++;
            System.out.print(iterations + " ");
            for (int i = 0; i < size - 2; i++) {
                for (int j = i + 1; j < size - 1; j++) {
                    for (int k = j + 1; k < size; k++) {
                        List<Integer> newTour = swapEdges(tour, i, j, k);
                        double newDistance = calculateTourDistance(newTour, distances);
                        if (newDistance < calculateTourDistance(tour, distances)) {
                            tour = newTour;
                            improved = true;
                        }
                    }
                }
            }
        }
        return tour;
    }
}
