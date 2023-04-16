package com.tsp.TSP_Spring;

import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class TSP {
    public static List<Integer> tsp() {

        double totalDistance = 0.0;

        // Step 1: Read or generate the input data

        int[][] graph = ReadCoOrdinates.readGraphFromFile("teamproject.csv");
        GraphInfo graphInfo = ReadCoOrdinates
                .readGraphInfoFromFile("teamproject.csv");

        // Step 2: Build an initial solution
        // int[] initialSolution = InitialTour.buildInitialSolution(graph);
        int[] initialSolution = InitialTour.buildInitialSolution(graphInfo.getGraph());
        List<Integer> initialList = new ArrayList<>();
        for (int i = 0; i < initialSolution.length; i++) {
            initialList.add(initialSolution[i]);
        }

        System.out.println("Initial tour : " + initialList);
        // totalDistance = TourDistance.tourDistance(initialList, graph);
        totalDistance = TourDistance.tourDistance(initialList, graphInfo.getGraph());
        // totalDistance = TourDistance.getActualTourDistance(initialList, graphInfo);
        System.out.println("\nTotal initial distance: " + totalDistance);
        System.out.println();

        // Step 3: Apply the Christofides algorithm
        // int[] christofidesSolution =
        // Christofides.applyChristofidesAlgorithm(graph,initialSolution);
        int[] christofidesSolution = Christofides.applyChristofidesAlgorithm(graphInfo.getGraph(),
                initialSolution);
        List<Integer> christofidesList = new ArrayList<>();
        for (int i = 0; i < christofidesSolution.length; i++) {
            christofidesList.add(christofidesSolution[i]);
        }

        System.out.print("Christofides Solution= " + christofidesList);
        // totalDistance = TourDistance.tourDistance(christofidesList, graph);
        totalDistance = TourDistance.tourDistance(christofidesList, graphInfo.getGraph());

        // CreateGraph.createChart(TourDistance.getCoords(christofidesList, graphInfo));
        totalDistance = TourDistance.tourDistance(christofidesList, graphInfo.getGraph());
        System.out.println("\nTotal distance w Christofides: " + totalDistance);

        int[] newTour = TwoOpt.twoOpt(christofidesSolution, graphInfo, graphInfo.getGraph());
        List<Integer> twoOptNewList = new ArrayList<>();

        for (int i = 0; i < newTour.length; i++) {
            twoOptNewList.add(newTour[i]);
        }

        return twoOptNewList;
    }

}
