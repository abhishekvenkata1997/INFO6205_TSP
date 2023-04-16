package com.tsp.TSP_Spring;

import java.util.*;

public class GraphInfo {
    private int[][] graph;
    private Map<Integer, double[]> coordinates;
    private int numVertices;

    public GraphInfo(int[][] graph, Map<Integer, double[]> coordinates, int numVertices) {
        this.graph = graph;
        this.coordinates = coordinates;
        this.numVertices = numVertices;
    }

    public int[][] getGraph() {
        return graph;
    }

    public void setGraph(int[][] graph) {
        this.graph = graph;
    }

    public Map<Integer, double[]> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Map<Integer, double[]> coordinates) {
        this.coordinates = coordinates;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }
}