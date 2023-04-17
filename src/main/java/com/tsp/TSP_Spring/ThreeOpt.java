package com.tsp.TSP_Spring;

import java.util.*;

public class ThreeOpt {

    // Swap two edges in a tour and return the resulting tour
    private static List<Integer> swap(List<Integer> tour, int i, int j, int k) {
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
    static double distance(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371; // Radius of the earth in km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    // Calculate the total distance of a tour
    static double tourDistance(List<Integer> tour, double[][] distances) {
        double distance = 0;
        for (int i = 0; i < tour.size() - 1; i++) {
            distance += distances[tour.get(i)][tour.get(i + 1)];
        }
        distance += distances[tour.get(tour.size() - 1)][tour.get(0)];
        return distance;
    }

    // Run the 3-opt algorithm on a tour and return the optimized tour
    public static List<Integer> threeOpt(List<Integer> tour, double[][] distances) {
        int size = tour.size();
        boolean improved = true;
        int cnt = 0;
        while (improved) {
            improved = false;
            cnt++;
            System.out.print(cnt + " ");
            for (int i = 0; i < size - 2; i++) {
                for (int j = i + 1; j < size - 1; j++) {
                    for (int k = j + 1; k < size; k++) {
                        List<Integer> newTour = swap(tour, i, j, k);
                        double newDistance = tourDistance(newTour, distances);
                        if (newDistance < tourDistance(tour, distances)) {
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
