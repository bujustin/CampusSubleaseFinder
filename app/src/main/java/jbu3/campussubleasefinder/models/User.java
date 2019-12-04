package jbu3.campussubleasefinder.models;

public class User {
    public int id;
    public String name;
    public int numConnections;
    public double rating;

    public User(int id, String name, int numConnections, double rating) {
        this.id = id;
        this.name = name;
        this.numConnections = numConnections;
        this.rating = rating;
    }
}
