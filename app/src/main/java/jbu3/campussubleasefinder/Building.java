package jbu3.campussubleasefinder;

public class Building {
    String address = "";
    int numSubleases = 0;
    int numConnections = 0;
    double rating = 0;
    String imageURL = "";
    public Building(String address, int numSubleases, int numConnections, double rating, String imageURL) {
        this.address = address;
        this.numConnections = numConnections;
        this.numSubleases = numSubleases;
        this.rating = rating;
        this.imageURL = imageURL;
    }
}
