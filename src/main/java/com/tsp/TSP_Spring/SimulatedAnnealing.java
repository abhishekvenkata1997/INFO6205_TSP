package com.tsp.TSP_Spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulatedAnnealing {
    private double[][] distanceMatrix;
    private List<Integer> currentTour;
    private double currentTourDistance;

    public SimulatedAnnealing(double[][] distanceMatrix, List<Integer> initialTour) {
        this.distanceMatrix = distanceMatrix;
        this.currentTour = initialTour;
        this.currentTourDistance = calculateTourDistance(currentTour);
    }

    public List<Integer> solve(int maxIterations, double initialTemperature, double coolingRate) {
        Random random = new Random();

        double currentTemperature = initialTemperature;
        List<Integer> bestTour = new ArrayList<>(currentTour);
        double bestTourDistance = currentTourDistance;

        int iterations = 0;
        while (iterations < maxIterations) {
            List<Integer> newTour = getNewTour();
            double newTourDistance = calculateTourDistance(newTour);

            double delta = newTourDistance - currentTourDistance;
            if (delta < 0 || Math.exp(-delta / currentTemperature) > random.nextDouble()) {
                currentTour = newTour;
                currentTourDistance = newTourDistance;
            }

            if (currentTourDistance < bestTourDistance) {
                bestTour = new ArrayList<>(currentTour);
                bestTourDistance = currentTourDistance;
            }

            currentTemperature *= coolingRate;
            iterations++;
        }

        return bestTour;
    }

    public List<Integer> getNewTour() {
        Random random = new Random();

        int i = random.nextInt(currentTour.size() - 1);
        int j = random.nextInt(currentTour.size() - 1);

        if (i == j) {
            j = (j + 1) % currentTour.size();
        }

        List<Integer> newTour = new ArrayList<>(currentTour);
        swap(newTour, i, j);

        return newTour;
    }

    public double calculateTourDistance(List<Integer> tour) {
        double tourDistance = 0.0;
        for (int i = 0; i < tour.size() - 1; i++) {
            int fromCity = tour.get(i);
            int toCity = tour.get(i + 1);
            tourDistance += distanceMatrix[fromCity][toCity];
        }
        tourDistance += distanceMatrix[tour.get(tour.size() - 1)][tour.get(0)];
        return tourDistance;
    }

    public void swap(List<Integer> tour, int i, int j) {
        int temp = tour.get(i);
        tour.set(i, tour.get(j));
        tour.set(j, temp);
    }

}
