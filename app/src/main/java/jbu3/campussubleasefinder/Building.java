package jbu3.campussubleasefinder;

public class Building {
    String address = "";
    int numSubleases = 0;
    int numConnections = 0;
    double rating = 0;
    public Building(String address, int numSubleases, int numConnections, double rating) {
        this.address = address;
        this.numConnections = numConnections;
        this.numSubleases = numSubleases;
        this.rating = rating;
    }
}
