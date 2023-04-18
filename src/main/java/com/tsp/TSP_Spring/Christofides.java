package com.tsp.TSP_Spring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class Christofides {
    public static int[] applyChristofidesAlgorithm(double[][] graph) {
        // Find the minimum spanning tree
        double[][] minimumSpanningTree = findMinimumSpanningTree(graph);

        // Identify odd-degree vertices in the minimum spanning tree
        List<Integer> oddDegreeVertices = identifyOddDegreeVertices(minimumSpanningTree);

        // Find a perfect matching for odd-degree vertices
        double[][] perfectMatching = findPerfectMatching(graph, oddDegreeVertices);

        // Combine the minimum spanning tree and the perfect matching
        double[][] combinedGraph = combineMinimumSpanningTreeAndPerfectMatching(minimumSpanningTree, perfectMatching);

        // find Eulerian Tour of Combined Graph
        List<Integer> eulerianTour = findEulerianTour(combinedGraph);

        // convert to hamiltonian cycle
        int[] hamiltonianCycle = convertToHamiltonianCycle(eulerianTour);

        // Improve the initial solution using the combined graph
        // int[] improvedSolution = improveSolutionWithCombinedGraph(combinedGraph,
        // hamiltonianCycle);
        // initialSolution);

        int[] improvedPath = improveHamiltonianPath(graph, hamiltonianCycle);
        return improvedPath;
    }

    public static double[][] findMinimumSpanningTree(double[][] graph) {
        int numVertices = graph.length;
        double[][] minimumSpanningTree = new double[numVertices][numVertices];

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
            double minWeight = Double.MAX_VALUE;
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

    public static List<Integer> identifyOddDegreeVertices(double[][] minimumSpanningTree) {
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

    public static double[][] findPerfectMatching(double[][] graph, List<Integer> oddDegreeVertices) {
        int numVertices = graph.length;
        double[][] perfectMatching = new double[numVertices][numVertices]; //
        // Initialize perfect matching as all zeros

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
    private static int getDegree(double[][] graph, int vertex) {
        int degree = 0;
        for (int i = 0; i < graph.length; i++) {
            degree += graph[vertex][i];
        }
        return degree;
    }

    public static double[][] combineMinimumSpanningTreeAndPerfectMatching(double[][] minimumSpanningTree,
            double[][] perfectMatching) {
        int numVertices = minimumSpanningTree.length;
        double[][] combinedGraph = new double[numVertices][numVertices]; // Initialize combined graph as all zeros

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

    public static int[] improveSolutionWithCombinedGraph(double[][] combinedGraph, int[] initialSolution) {
        // Logic to improve the initial solution using the combined graph
        // You can implement a 2-opt or other local search algorithm here

        int[] improvedSolution = initialSolution.clone(); // Clone the initial solution as a starting point

        // Perform 2-opt or other local search algorithm on the improved solution

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

    public static List<Integer> findEulerianTour(double[][] graph) {
        int n = graph.length;
        int startVertex = 0; // choose any starting vertex
        List<Integer> tour = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            int u = stack.peek();
            boolean foundNeighbor = false;
            for (int v = 0; v < n; v++) {
                if (graph[u][v] > 0) {
                    graph[u][v]--; // remove edge from graph
                    graph[v][u]--; // remove edge from graph
                    stack.push(v);
                    foundNeighbor = true;
                    break;
                }
            }
            if (!foundNeighbor) {
                stack.pop();
                tour.add(u);
            }
        }

        // Reverse the tour to get the correct order
        Collections.reverse(tour);
        return tour;
    }

    public static int[] convertToHamiltonianCycle(List<Integer> eulerianTour) {
        int n = eulerianTour.size();
        boolean[] visited = new boolean[n];
        int[] hamiltonianCycle = new int[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            int vertex = eulerianTour.get(i);
            if (!visited[vertex]) {
                hamiltonianCycle[count++] = vertex;
                visited[vertex] = true;
            }
        }

        // Add the starting vertex to the end to complete the cycle
        hamiltonianCycle[n - 1] = hamiltonianCycle[0];

        // Create a new array with only the non-zero elements
        int[] trimmedHamiltonianCycle = new int[count];
        for (int i = 0; i < count; i++) {
            trimmedHamiltonianCycle[i] = hamiltonianCycle[i];
        }

        return trimmedHamiltonianCycle;
    }

    public static int[] improveHamiltonianPath(double[][] graph, int[] path) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        visited[path[0]] = true;

        for (int i = 1; i < n; i++) {
            int current = path[i - 1];
            int next = -1;
            double minDistance = Double.POSITIVE_INFINITY;

            for (int j = 0; j < n; j++) {
                if (!visited[j] && graph[current][j] < minDistance) {
                    next = j;
                    minDistance = graph[current][j];
                }
            }

            path[i] = next;
            visited[next] = true;
        }

        return path;
    }

}
