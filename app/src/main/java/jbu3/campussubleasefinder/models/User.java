package jbu3.campussubleasefinder.models;

import java.util.ArrayList;

public class User {
    public int id;
    public String name;
    public String about;
    public String email;
    public String phone;
    public String password;

    public int numConnections;
    public double rating;

    public ArrayList<Integer> connectionsIds;

    public User(int id, String name, int numConnections, double rating,
                String about, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.numConnections = numConnections;
        this.rating = rating;

        this.about = about;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
}
