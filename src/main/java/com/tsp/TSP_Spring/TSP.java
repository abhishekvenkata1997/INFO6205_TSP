package com.tsp.TSP_Spring;

import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class TSP {

    public ReadCoOrdinates readCoOrdinates = new ReadCoOrdinates();

    public double graph[][] = readCoOrdinates.readGraphFromFile("teamproject.csv");

    // method for backend run only
    public void tspBackend() {

        List<double[]> coOrdinates = readCoOrdinates.getCoOrdinates();

        List<String> crimeIDs = readCoOrdinates.getCrimeIDs();

        double distance = 0.0;

        System.out.println("MST Tour Start..");
        MST mst = new MST();
        mst.primMST(graph);
        System.out.println("MST Tour Ended..");



        System.out.println("Christofides Tour Start");
        int[] christofidesSolution = Christofides.applyChristofidesAlgorithm(graph);
        List<Integer> christofidesList = new ArrayList<>();
        for (int i = 0; i < christofidesSolution.length; i++) {
            christofidesList.add(christofidesSolution[i]);
            System.out.print("Crime ID: " + crimeIDs.get(i));
            System.out.print(", Latitide: " + coOrdinates.get(i)[1]);
            System.out.print(", Longitude: " + coOrdinates.get(i)[0]);
            System.out.println();
        }

        distance = TourDistance.tourDistance(christofidesList, graph);
        System.out.println("\nTotal distance w Christofides: " + distance * 1000);
        System.out.println("Christofides Tour Ended..");

        System.out.println("Two-Opt Tour Start..");
        int[] newTour = TwoOpt.twoOpt(christofidesSolution, graph);
        List<Integer> twoOptNewList = new ArrayList<>();
        for (int i = 0; i < newTour.length; i++) {
            twoOptNewList.add(newTour[i]);
            System.out.print("Crime ID: " + crimeIDs.get(i));
            System.out.print(", Latitide: " + coOrdinates.get(i)[1]);
            System.out.print(", Longitude: " + coOrdinates.get(i)[0]);
            System.out.println();
        }
        System.out.println("2-opt distance=" + TourDistance.tourDistance(twoOptNewList, graph));
        System.out.println("Two-opt Tour Ended..");

        System.out.println("Three-opt Tour Start..");
        List<Integer> threeOptNewList = new ArrayList<>();
        threeOptNewList = ThreeOpt.threeOptAlgorithm(christofidesList, graph);
        threeOptNewList.forEach(i -> {
            System.out.print("Crime ID: " + crimeIDs.get(i));
            System.out.print(", Latitide: " + coOrdinates.get(i)[1]);
            System.out.print(", Longitude: " + coOrdinates.get(i)[0]);
            System.out.println();
        });

        System.out.println("Three opt tour distance: " + TourDistance.tourDistance(threeOptNewList, graph) * 1000);
        System.out.println("Three-opt Tour Ended..");

        System.out.println("Ant Colony Optimization Tour Start..");
        int numIterations = 100;
        int numAnts = 23;
        double alpha = 3.0;
        double beta = 6.0;
        double evaporation = 0.1;

        ACO aco = new ACO(graph.length, graph, alpha, beta, evaporation, 0.15, numAnts, 12345L);
        aco.solve(numIterations);

        int[] acoTour = aco.getBestTour();
        List<Integer> ACONewList = new ArrayList<Integer>();
        for (int i = 0; i < acoTour.length; i++) {
            ACONewList.add(acoTour[i]);
            System.out.print("Crime ID: " + crimeIDs.get(i));
            System.out.print(", Latitide: " + coOrdinates.get(i)[1]);
            System.out.print(", Longitude: " + coOrdinates.get(i)[0]);
            System.out.println();
        }

        System.out.println("Length of best tour ACO: " + TourDistance.tourDistance(ACONewList, graph) * 1000);
        System.out.println("Ant Colony Optimization Tour Ended..");

        System.out.println("Simulated Annealing Tour Start..");
        Collections.shuffle(christofidesList);
        SimulatedAnnealing sa = new SimulatedAnnealing(graph, christofidesList);
        List<Integer> bestTour = sa.solve(10000000, 1000000, 0.98);
        bestTour.forEach(i -> {
            System.out.print("Crime ID: " + crimeIDs.get(i));
            System.out.print(", Latitide: " + coOrdinates.get(i)[1]);
            System.out.print(", Longitude: " + coOrdinates.get(i)[0]);
            System.out.println();
        });
        System.out.println("Initial distance: " + sa.calculateTourDistance(christofidesList) * 1000);
        System.out.println("Tour distance: " + sa.calculateTourDistance(bestTour) * 1000);
        System.out.println("Simulated Annealing Tour Ended..");
    }

    // method for ui api calls
    public List<Integer> tsp(int a) {

        // mst start
        MST t = new MST();
        t.primMST(graph);
        // mst end

        // Christofides algorithm start
        int[] christofidesSolution = Christofides.applyChristofidesAlgorithm(graph);
        List<Integer> christofidesList = new ArrayList<>();
        for (int i = 0; i < christofidesSolution.length; i++) {
            christofidesList.add(christofidesSolution[i]);
        }
        System.out.println(
                "\nTotal distance w Christofides: " + TourDistance.tourDistance(christofidesList, graph) * 1000);
        // Christofides algorithm start

        if (a == 0) {
            return christofidesList;
        }
        // Two-opt
        if (a == 1) {
            int[] newTour = TwoOpt.twoOpt(christofidesSolution, graph);
            List<Integer> twoOptNewList = new ArrayList<>();

            for (int i = 0; i < newTour.length; i++) {
                twoOptNewList.add(newTour[i]);
            }
            System.out.print("2-opt distance=" + TourDistance.tourDistance(twoOptNewList, graph));
            return twoOptNewList;
        }
        // three-opt
        if (a == 2) {
            List<Integer> threeOptNewList = new ArrayList<>();
            threeOptNewList = ThreeOpt.threeOptAlgorithm(christofidesList, graph);
            System.out.print("Three opt tour distance: " + TourDistance.tourDistance(threeOptNewList, graph));
            return threeOptNewList;
        }
        // Ant Colony Optimization
        if (a == 3) {
            int numIterations = 100;
            int numAnts = 23;
            double alpha = 3.0;
            double beta = 6.0;
            double evaporation = 0.1;

            ACO aco = new ACO(graph.length, graph, alpha, beta, evaporation, 0.15, numAnts, 12345L);
            aco.solve(numIterations);

            int[] acoTour = aco.getBestTour();
            List<Integer> ACONewList = new ArrayList<Integer>();
            for (int i = 0; i < acoTour.length; i++) {
                ACONewList.add(acoTour[i]);
            }
            System.out.println("Length of best tour ACO: " + TourDistance.tourDistance(ACONewList, graph));
            return ACONewList;

        }
        // Simulated Annelaing
        if (a == 4) {
            Collections.shuffle(christofidesList);
            SimulatedAnnealing sa = new SimulatedAnnealing(graph, christofidesList);
            List<Integer> bestTour = sa.solve(10000000, 100000, 0.995);
            System.out.println("Initial distance: " + sa.calculateTourDistance(christofidesList));
            System.out.println("Tour distance: " + sa.calculateTourDistance(bestTour));
            return bestTour;
        }
        return christofidesList;
    }

    public double[][] getGraph() {
        return graph;
    }

}
