package com.tsp.TSP_Spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ACO {
    private int numCities; // number of cities
    private double[][] distanceMatrix; // distance matrix
    private double[][] pheromoneMatrix; // pheromone matrix
    private double alpha; // pheromone importance factor
    private double beta; // heuristic information importance factor
    private double evaporation; // pheromone evaporation factor
    private int numAnts; // number of ants
    private int[] bestTour; // best tour found
    private double bestTourLength; // length of best tour
    private Random random; // random number generator

    public ACO(int numCities, double[][] distanceMatrix, double alpha, double beta, double evaporation,
            double initialPheromone, int numAnts, long seed) {
        this.numCities = numCities;
        this.distanceMatrix = distanceMatrix;
        this.alpha = alpha;
        this.beta = beta;
        this.evaporation = evaporation;
        this.numAnts = numAnts;
        this.random = new Random(seed);

        // initialize the pheromone matrix with the initial value
        this.pheromoneMatrix = new double[numCities][numCities];
        for (int i = 0; i < numCities; i++) {
            Arrays.fill(this.pheromoneMatrix[i], initialPheromone);
        }
    }

    public void solve(int numIterations) {
        // initialize the best tour
        this.bestTour = null;
        this.bestTourLength = Double.POSITIVE_INFINITY;

        for (int iteration = 0; iteration < numIterations; iteration++) {
            // create a list to store the tours of all ants
            List<int[]> antTours = new ArrayList<>();

            // construct a tour for each ant
            for (int ant = 0; ant < numAnts; ant++) {
                int startCity = random.nextInt(numCities);
                int[] tour = constructTour(startCity);
                antTours.add(tour);

                // update the best tour found so far
                double tourLength = TourDistance.calculateTourDistance(distanceMatrix, tour);
                if (tourLength < bestTourLength) {
                    bestTour = tour;
                    bestTourLength = tourLength;
                }
            }

            // update the pheromone matrix based on the tours of all ants
            updatePheromoneMatrix(antTours);
        }
    }

    public int[] getBestTour() {
        return bestTour;
    }

    public double getBestTourLength() {
        return bestTourLength;
    }

    private int[] constructTour(int startCity) {
        // create a list of unvisited cities
        List<Integer> unvisitedCities = new ArrayList<>();
        for (int i = 0; i < numCities; i++) {
            if (i != startCity) {
                unvisitedCities.add(i);
            }
        }

        // create a tour starting from the start city
        int[] tour = new int[numCities];
        tour[0] = startCity;
        for (int i = 1; i < numCities; i++) {
            // choose the next city to visit using the probability distribution
            int nextCity = chooseNextCity(tour[i - 1], unvisitedCities);
            tour[i] = nextCity;

            // remove the next
            // city from the list of unvisited cities
            unvisitedCities.remove((Integer) nextCity);
        }

        return tour;
    }

    private int chooseNextCity(int currentCity, List<Integer> unvisitedCities) {
        // calculate the probability of choosing each unvisited city as the next city
        double[] probabilities = new double[numCities];
        double totalProbability = 0.0;
        for (int i = 0; i < unvisitedCities.size(); i++) {
            int city = unvisitedCities.get(i);
            double pheromone = pheromoneMatrix[currentCity][city];
            double distance = distanceMatrix[currentCity][city];
            double probability = Math.pow(pheromone, alpha) * Math.pow(1.0 / distance, beta);
            probabilities[i] = probability;
            totalProbability += probability;
        }

        // choose a city randomly based on the probability distribution
        double randomValue = random.nextDouble() * totalProbability;
        double cumulativeProbability = 0.0;
        for (int i = 0; i < unvisitedCities.size(); i++) {
            int city = unvisitedCities.get(i);
            cumulativeProbability += probabilities[i];
            if (cumulativeProbability >= randomValue) {
                return city;
            }
        }

        // if the loop ends without returning a city, return the last city in the list
        return unvisitedCities.get(unvisitedCities.size() - 1);
    }

    private double calculateTourLength(int[] tour) {
        double tourLength = 0.0;
        for (int i = 0; i < numCities; i++) {
            int city1 = tour[i];
            int city2 = tour[(i + 1) % numCities];
            tourLength += distanceMatrix[city1][city2];
        }
        return tourLength;
    }

    private void updatePheromoneMatrix(List<int[]> antTours) {
        // evaporate the pheromone on all edges
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                pheromoneMatrix[i][j] *= (1.0 - evaporation);
            }
        }

        // add pheromone to the edges visited by each ant
        for (int[] tour : antTours) {
            double tourLength = calculateTourLength(tour);
            double tourPheromone = 1.0 / tourLength;
            for (int i = 0; i < numCities; i++) {
                int city1 = tour[i];
                int city2 = tour[(i + 1) % numCities];
                pheromoneMatrix[city1][city2] += tourPheromone;
                pheromoneMatrix[city2][city1] += tourPheromone;
            }
        }
    }
}
