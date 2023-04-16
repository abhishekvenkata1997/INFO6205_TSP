package com.tsp.TSP_Spring;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Christofides {
    public static int[] applyChristofidesAlgorithm(int[][] graph, int[] initialSolution) {
        // Find the minimum spanning tree
        int[][] minimumSpanningTree = findMinimumSpanningTree(graph);

        // Identify odd-degree vertices in the minimum spanning tree
        List<Integer> oddDegreeVertices = identifyOddDegreeVertices(minimumSpanningTree);

        // Find a perfect matching for odd-degree vertices
        int[][] perfectMatching = findPerfectMatching(graph, oddDegreeVertices);

        // Combine the minimum spanning tree and the perfect matching
        int[][] combinedGraph = combineMinimumSpanningTreeAndPerfectMatching(minimumSpanningTree, perfectMatching);

        // Improve the initial solution using the combined graph
        int[] improvedSolution = improveSolutionWithCombinedGraph(combinedGraph, initialSolution);

        return improvedSolution;
    }

    public static int[][] findMinimumSpanningTree(int[][] graph) {
        int numVertices = graph.length;
        int[][] minimumSpanningTree = new int[numVertices][numVertices];

        // Initialize the minimum spanning tree with all zeros
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                minimumSpanningTree[i][j] = 0;
            }
        }

        // Keep track of vertices included in the minimum spanning tree
        boolean[] included = new boolean[numVertices];

        // Choose the starting vertex (e.g., vertex 0)
        included[0] = true;

        // Perform Prim's algorithm
        for (int count = 0; count < numVertices - 1; count++) {
            int minWeight = Integer.MAX_VALUE;
            int u = -1;
            int v = -1;

            // Find the minimum weight edge from included vertices to non-included vertices
            for (int i = 0; i < numVertices; i++) {
                if (included[i]) {
                    for (int j = 0; j < numVertices; j++) {
                        if (!included[j] && graph[i][j] > 0 && graph[i][j] < minWeight) {
                            minWeight = graph[i][j];
                            u = i;
                            v = j;
                        }
                    }
                }
            }

            // Include the vertex with the minimum weight edge
            included[v] = true;

            // Add the edge to the minimum spanning tree
            minimumSpanningTree[u][v] = minWeight;
            minimumSpanningTree[v][u] = minWeight;
        }

        return minimumSpanningTree;
    }

    public static List<Integer> identifyOddDegreeVertices(int[][] minimumSpanningTree) {
        List<Integer> oddDegreeVertices = new ArrayList<>();
        int numVertices = minimumSpanningTree.length;

        for (int i = 0; i < numVertices; i++) {
            int degree = 0;
            for (int j = 0; j < numVertices; j++) {
                degree += minimumSpanningTree[i][j];
            }

            if (degree % 2 != 0) {
                oddDegreeVertices.add(i);
            }
        }

        return oddDegreeVertices;
    }

    public static int[][] findPerfectMatching(int[][] graph, List<Integer> oddDegreeVertices) {
        int numVertices = graph.length;
        int[][] perfectMatching = new int[numVertices][numVertices]; // Initialize perfect matching as all zeros

        // Logic to find a perfect matching, such as using a greedy approach,
        // Edmonds' blossom algorithm, or other matching algorithms
        // Here, we use a simple greedy approach to match the odd-degree vertices

        // Sort the odd-degree vertices in ascending order of their degree
        oddDegreeVertices.sort(Comparator.comparingInt(v -> getDegree(graph, v)));

        // Match the odd-degree vertices greedily
        for (int i = 0; i < oddDegreeVertices.size(); i += 2) {
            int u = oddDegreeVertices.get(i);
            int v = oddDegreeVertices.get(i + 1);
            perfectMatching[u][v] = 1;
            perfectMatching[v][u] = 1;
        }

        return perfectMatching;
    }

    // Helper function to get the degree of a vertex in a graph represented as an
    // adjacency matrix
    private static int getDegree(int[][] graph, int vertex) {
        int degree = 0;
        for (int i = 0; i < graph.length; i++) {
            degree += graph[vertex][i];
        }
        return degree;
    }

    public static int[][] combineMinimumSpanningTreeAndPerfectMatching(int[][] minimumSpanningTree,
            int[][] perfectMatching) {
        int numVertices = minimumSpanningTree.length;
        int[][] combinedGraph = new int[numVertices][numVertices]; // Initialize combined graph as all zeros

        // Logic to combine the minimum spanning tree and perfect matching
        // by adding the edges from the perfect matching to the minimum spanning tree
        // Here, we simply add the edges from the perfect matching to the minimum
        // spanning tree
        // to create the combined graph

        // Copy the edges from the minimum spanning tree to the combined graph
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                combinedGraph[i][j] = minimumSpanningTree[i][j];
            }
        }

        // Add the edges from the perfect matching to the combined graph
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (perfectMatching[i][j] == 1) {
                    combinedGraph[i][j] = 1;
                }
            }
        }

        return combinedGraph;
    }

    public static int[] improveSolutionWithCombinedGraph(int[][] combinedGraph, int[] initialSolution) {
        // Logic to improve the initial solution using the combined graph
        // You can implement a 2-opt or other local search algorithm here

        int[] improvedSolution = initialSolution.clone(); // Clone the initial solution as a starting point

        // Perform 2-opt or other local search algorithm on the improved solution

        // Placeholder logic for illustration purposes:
        // Here, we simply reverse the order of the vertices in the first half of the
        // initial solution
        // to create a new improved solution
        int numVertices = combinedGraph.length;
        int startIndex = 0;
        int endIndex = numVertices / 2;
        while (startIndex < endIndex) {
            int temp = improvedSolution[startIndex];
            improvedSolution[startIndex] = improvedSolution[endIndex];
            improvedSolution[endIndex] = temp;
            startIndex++;
            endIndex--;
        }

        return improvedSolution;
    }

}
