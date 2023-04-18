package com.tsp.TSP_Spring;

public class Ant {

    private int sizeOfTrail;
    private int[] trail;
    private boolean[] visited;

    public Ant(int sizeOfTrail) {
        this.sizeOfTrail = sizeOfTrail;
        this.trail = new int[sizeOfTrail];
        this.visited = new boolean[sizeOfTrail];
    }

    public void visitCity(int currentIndex, int city) {
        trail[currentIndex + 1] = city;
        visited[city] = true;
    }

    public boolean isVisited(int i) {
        return visited[i];
    }

    public double calculateTrailLength(double[][] graph) {
        double length = graph[trail[sizeOfTrail - 1]][trail[0]];
        for (int i = 0; i < sizeOfTrail - 1; i++) {
            length += graph[trail[i]][trail[i + 1]];
        }
        return length;
    }

    public void clearVisitedCities() {
        for (int i = 0; i < sizeOfTrail; i++) {
            visited[i] = false;
        }
    }

}
