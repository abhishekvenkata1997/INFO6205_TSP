package com.tsp.TSP_Spring;

import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.*;

public class TSP {

    public double graph[][] = new ReadCoOrdinates().readGraphFromFile("teamproject.csv");
    public List<Integer> tsp(int a) {

        double totalDistance = 0.0;

        // Step 1: Read or generate the input data

        // double[][] graph = ReadCoOrdinates.readGraphFromFile("teamproject.csv");
        // GraphInfo graphInfo =
        // ReadCoOrdinates.readGraphInfoFromFile("teamproject.csv");

        // Step 2: Build an initial solution
        int[] initialSolution = InitialTour.buildInitialSolution(graph);
        List<Integer> initialList = new ArrayList<>();
        for (int i = 0; i < initialSolution.length; i++) {
            initialList.add(initialSolution[i]);
        }

        System.out.println("Initial tour : " + initialList);
        // totalDistance = TourDistance.tourDistance(initialList, graph);
        totalDistance = TourDistance.tourDistance(initialList, graph);
        // totalDistance = TourDistance.getActualTourDistance(initialList, graphInfo);
        System.out.println("\nTotal initial distance: " + totalDistance);
        System.out.println();

        System.out.println("Graph : " + graph.length);

        // Step 3: Apply the Christofides algorithm
        // int[] christofidesSolution =
        // Christofides.applyChristofidesAlgorithm(graph,initialSolution);
        int[] christofidesSolution = Christofides.applyChristofidesAlgorithm(graph, initialSolution);
        List<Integer> christofidesList = new ArrayList<>();
        for (int i = 0; i < christofidesSolution.length; i++) {
            christofidesList.add(christofidesSolution[i]);
        }

        System.out.print("Christofides Solution= " + christofidesList);
        // totalDistance = TourDistance.tourDistance(christofidesList, graph);
        totalDistance = TourDistance.tourDistance(christofidesList, graph);

        System.out.println("\nTotal distance w Christofides: " + totalDistance);

        if (a == 0) {
            return christofidesList;
        }
        if (a == 1) {
            int[] newTour = TwoOpt.twoOpt(initialSolution, graph);
            List<Integer> twoOptNewList = new ArrayList<>();

            for (int i = 0; i < newTour.length; i++) {
                twoOptNewList.add(newTour[i]);
            }
            System.out.print("2-opt distance=" + TourDistance.tourDistance(twoOptNewList, graph));
            return twoOptNewList;
        }

        if (a == 2) {
            // Run the 3-opt algorithm on the tour
            // Create an initial tour
            int n = graph.length;
            List<Integer> threeOptNewList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                threeOptNewList.add(i);
            }

            Collections.shuffle(threeOptNewList);
            threeOptNewList = ThreeOpt.threeOptAlgorithm(christofidesList, graph);

            // Print the optimized tour
            System.out.print("Three Opt : " + threeOptNewList);
            for (int i = 0; i < threeOptNewList.size(); i++) {
                System.out.print(threeOptNewList.get(i) + " ");
            }

            System.out.print("Three opt tour distance: " + TourDistance.tourDistance(threeOptNewList, graph));
            return threeOptNewList;
        }

        if (a == 3) {
            int numIterations = 100;
            int numAnts = 23;
            double alpha = 3.0;
            double beta = 6.0;
            double evaporation = 0.1;

            ACO aco = new ACO(graph.length, graph, alpha, beta, evaporation, 0.15, numAnts, 12345L);
            aco.solve(numIterations);

            int[] bestTour = aco.getBestTour();
            List<Integer> ACONewList = new ArrayList<Integer>();
            for (int i = 0; i < bestTour.length; i++) {
                ACONewList.add(bestTour[i]);
            }

            System.out.println("Best tour found ACO: " + Arrays.toString(bestTour));
            System.out.println("Length of best tour ACO: " + TourDistance.tourDistance(ACONewList, graph));

            return ACONewList;

        }

        if (a == 4) {
            Collections.shuffle(initialList);
            List<Integer> initialTour = initialList;// example initial tour
            SimulatedAnnealing solver = new SimulatedAnnealing(graph, initialTour);
            List<Integer> bestTour = solver.solve(80000000, 1000000, 0.98);
            System.out.println("Best tour found: " + bestTour);
            System.out.println("Initial distance: " + solver.calculateTourDistance(initialList));
            System.out.println("Tour distance: " + solver.calculateTourDistance(bestTour));
            return bestTour;
        }

        return christofidesList;
    }

    public double[][] getGraph() {

        double[][] graph = new ReadCoOrdinates().readGraphFromFile("teamproject.csv");
        return graph;
    }

}
