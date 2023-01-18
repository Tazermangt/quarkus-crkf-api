package fr.ot.tools;

public class Maths {
    
    private Maths(){}

    public static double getDistanceBetweentwoCoordinates(double latitudeA, double longitudeA, double latitudeB, double longitudeB) {
        return 2 * Math.asin(Math.sqrt(Math.pow(Math.sin((Math.toRadians(latitudeB - latitudeA)) / 2), 2) + Math.pow(Math.sin((Math.toRadians(longitudeB - longitudeA)) / 2), 2) * Math.cos((Math.toRadians(latitudeA))) * Math.cos(Math.toRadians(latitudeB)))) * 6371.009;
    }
    
}
