package com.tsp.TSP_Spring;

import java.util.List;

public class TourDistance {
    public static double calculateTourDistance(double[][] graph, int[] solution) {
        double totalDistance = 0.0;
        for (int i = 0; i < solution.length - 1; i++) {
            int currentVertex = solution[i];
            int nextVertex = solution[i + 1];
            totalDistance += graph[currentVertex][nextVertex];
        }
        // Add the distance from the last vertex back to the starting vertex for a tour
        totalDistance += graph[solution[solution.length - 1]][solution[0]];
        return totalDistance;
    }

    static double tourDistance(List<Integer> tour, double[][] distances) {
        double distance = 0;
        for (int i = 0; i < tour.size() - 1; i++) {
            distance += distances[tour.get(i)][tour.get(i + 1)];
        }
        distance += distances[tour.get(tour.size() - 1)][tour.get(0)];
        return distance;
    }

    static double getActualTourDistance(List<Integer> tour, GraphInfo graphInfo) {

        double distance = 0;
        double[][] coordinates = new double[graphInfo.getNumVertices()][graphInfo.getNumVertices()];

        // System.out.print("Getting inside actual tour");
        for (int i = 0; i < tour.size() - 1; i++) {
            // System.out.print("Getting inside actual tour");
            double[] coord1 = graphInfo.getCoordinates().get(tour.get(i));
            double[] coord2 = graphInfo.getCoordinates().get(tour.get(i + 1));
            coordinates[i] = coord1;
            if (coord1 != null && coord2 != null) {
                distance = distance + getCoordDistance(coord1, coord2);
            }
            // System.out.print(Arrays.toString(coord1) + " " + Arrays.toString(coord2) + "
            // " + distance + " ");
        }
        double[] coord1 = graphInfo.getCoordinates().get(tour.get(tour.size() - 1));
        double[] coord2 = graphInfo.getCoordinates().get(tour.get(1));
        distance = distance + getCoordDistance(coord1, coord2);
        return distance;
    }

    static double[][] getCoords(List<Integer> list, GraphInfo graphInfo) {
        double[][] coordinates = new double[list.size()][list.size()];

        for (int i = 0; i < list.size(); i++) {
            // System.out.print("Getting inside actual tour");
            double[] coord = graphInfo.getCoordinates().get(list.get(i));
            coordinates[i] = coord;
        }
        return coordinates;
    }

    static double getCoordDistance(double[] cord1, double[] cord2) {
        // Calculate the difference in x and y coordinates
        double diffX = cord1[0] - cord2[0];
        double diffY = cord1[1] - cord2[1];

        // Calculate the Euclidean distance between the two coordinates
        double distance = Math.sqrt(diffX * diffX + diffY * diffY);
        return distance;
    }

    public static final double RADIUS_OF_EARTH = 6371; // Earth's radius in kilometers

    public static double getDistance(double[] coord1, double[] coord2) {

        double lat1 = Math.toRadians(coord1[1]);
        double lon1 = Math.toRadians(coord1[0]);
        double lat2 = Math.toRadians(coord2[1]);
        double lon2 = Math.toRadians(coord2[0]);

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;

        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2)
                + Math.cos(lat1) * Math.cos(lat2)
                        * Math.sin(dlon / 2) * Math.sin(dlon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = RADIUS_OF_EARTH * c;
        return distance;
    }

}
