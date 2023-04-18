package com.tsp.TSP_Spring;

public class MST1 {
    private static final int V = 585;

    public int minKey(double key[], boolean mstSet[]) {
        double min = Double.MAX_VALUE;
        int min_index = -1;

        for (int v = 0; v < V; v++) {
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        }
        return min_index;
    }

    public void printMST(int parent[], double graph[][]) {
        System.out.println("MST" + parent[1]);
        double dist = 0.0;
        for (int i = 1; i < V; i++) {
            dist += graph[i][parent[i]];
            // System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
        }
        // System.out.println(TourDistance.calculateTourDistance(graph, parent));
        System.out.println(dist + graph[parent[parent.length - 1]][parent[1]]);

    }

    public void primMST(double graph[][]) {
        int parent[] = new int[V];
        double key[] = new double[V];
        boolean mstSet[] = new boolean[V];

        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, mstSet);
            mstSet[u] = true;
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }
        printMST(parent, graph);
    }

}