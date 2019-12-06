package jbu3.campussubleasefinder.models;

import java.util.ArrayList;

public class User {
    public int id;
    public String name;
    public String about;
    public String email;
    public String phone;
    public double rating;
    public ArrayList<Sublease> subleases;
    public ArrayList<Review> reviews;
    public ArrayList<User> connections;
    public Integer[] connectionIDs;

    public User(int id, String name, String email, String phone, String about, Integer[] connectionIDs) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.about = about;
        this.connectionIDs = connectionIDs;
        this.subleases = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.connections = new ArrayList<>();
    }

    public double getAvgRating() {
        double sum = 0;
        for (Review review : this.reviews) {
            sum += review.rating;
        }
        return sum / this.reviews.size();
    }
}
