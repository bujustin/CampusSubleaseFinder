package jbu3.campussubleasefinder.models;

public class User {
    public String name;
    public int numConnections;
    public double rating;

    public User(String name, int numConnections, double rating) {
        this.name = name;
        this.numConnections = numConnections;
        this.rating = rating;
    }
}
