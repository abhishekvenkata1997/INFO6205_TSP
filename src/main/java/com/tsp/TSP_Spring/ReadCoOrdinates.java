package com.tsp.TSP_Spring;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadCoOrdinates {

    private List<double[]> coordinates = new ArrayList<>();

    private List<String> crimeIDs = new ArrayList<>();

    public List<double[]> getCoOrdinates() {
        return coordinates;
    }

    public List<String> getCrimeIDs() {
        return crimeIDs;
    }

    // read input from csv file
    public double[][] readGraphFromFile(String filename) {

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean skipHeader = true;
            while ((line = br.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }
                String[] values = line.split(",");
                String crimeID = values[0];
                double longitude = Double.parseDouble(values[1].trim());
                double latitude = Double.parseDouble(values[2].trim());
                coordinates.add(new double[] { longitude, latitude });
                crimeIDs.add(crimeID);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int n = coordinates.size();
        double[][] graph = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double[] coord1 = coordinates.get(i);
                double[] coord2 = coordinates.get(j);
                double distance = distance(coord1[1], coord1[0], coord2[1], coord2[0]);
                graph[i][j] = distance;
                graph[j][i] = distance;
            }
        }
        coordinates.forEach((double[] arr) -> {
            // System.out.println("{ lat: " + arr[1] + ", lng: " + arr[0] + " },");
        });
        return graph;
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
