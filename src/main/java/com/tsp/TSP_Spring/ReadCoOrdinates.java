package com.tsp.TSP_Spring;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ReadCoOrdinates {

    private static List<double[]> coordinates = new ArrayList<>();

    // read input from csv file
    static int[][] readGraphFromFile(String filename) {

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean skipHeader = true;
            while ((line = br.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }
                String[] values = line.split(",");
                double longitude = Double.parseDouble(values[1].trim());
                double latitude = Double.parseDouble(values[2].trim());
                coordinates.add(new double[] { longitude, latitude });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int n = coordinates.size();
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double[] coord1 = coordinates.get(i);
                double[] coord2 = coordinates.get(j);
                int distance = (int) Math.round(
                        (Math.sqrt(Math.pow(coord1[0] - coord2[0], 2) + Math.pow(coord1[1] - coord2[1], 2)) * 10000));
                graph[i][j] = distance;
                graph[j][i] = distance;
            }
        }
        return graph;
    }

    static GraphInfo readGraphInfoFromFile(String filename) {
        List<double[]> coordinates = new ArrayList<>();
        Map<Integer, double[]> coordinatesMap = new HashMap<>(); // New map to store coordinates

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean skipHeader = true;
            int i = 1;
            while ((line = br.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }
                String[] values = line.split(",");
                int vertex = i++; // Assuming the vertex index is in the first column
                double longitude = Double.parseDouble(values[1].trim());
                double latitude = Double.parseDouble(values[2].trim());
                double[] coord = new double[] { longitude, latitude };
                coordinates.add(coord);
                coordinatesMap.put(vertex, coord); // Add mapping between vertex and coordinates
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int n = coordinates.size();
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double[] coord1 = coordinates.get(i);
                double[] coord2 = coordinates.get(j);
                // int distance = (int) Math.round(
                // (Math.sqrt(Math.pow(coord1[0] - coord2[0], 2) + Math.pow(coord1[1] -
                // coord2[1], 2)) * 10000));
                int distance = (int) distance(coord1[1], coord1[0], coord2[1], coord2[0]);

                graph[i][j] = distance;
                graph[j][i] = distance;
            }
        }
        return new GraphInfo(graph, coordinatesMap, n); // Return GraphInfo object with graph, coordinates, and number
                                                        // of vertices
    }

    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371; // Radius of the earth in km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
