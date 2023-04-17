package com.tsp.TSP_Spring;

public class InitialTour {

    public static int[] buildInitialSolution(double[][] graph) {
        int numNodes = graph.length; // Number of nodes in the graph
        int[] tour = new int[numNodes]; // Array to store the tour
        boolean[] visited = new boolean[numNodes]; // Array to keep track of visited nodes
        int currentNode = 0; // Start from node 0

        // Mark the starting node as visited
        visited[currentNode] = true;
        tour[0] = currentNode;

        // Iterate over the remaining nodes to build the tour
        for (int i = 1; i < numNodes; i++) {
            int nextNode = -1; // Initialize the next node to -1

            // Iterate over the neighbors of the current node
            for (int j = 0; j < numNodes; j++) {
                // Skip nodes that have already been visited
                if (!visited[j]) {
                    // If the next node has not been set yet or its edge weight is lower,
                    // update the next node
                    if (nextNode == -1 || graph[currentNode][j] < graph[currentNode][nextNode]) {
                        nextNode = j;
                    }
                }
            }

            // Mark the next node as visited and add it to the tour
            visited[nextNode] = true;
            tour[i] = nextNode;
            currentNode = nextNode; // Update the current node for the next iteration
        }

        return tour;
    }
}
