package jbu3.campussubleasefinder.models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Building {
    public String address = "";
    public int numSubleases = 0;
    public int numConnections = 0;
    public double rating = 0;
    public String imageURL = "";

    public Building(String address, int numSubleases, int numConnections, double rating, String imageURL) {
        this.address = address;
        this.numConnections = numConnections;
        this.numSubleases = numSubleases;
        this.rating = rating;
        this.imageURL = imageURL;
    }
}
