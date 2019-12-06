package jbu3.campussubleasefinder.models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Building {
    public int id;
    public String address = "";
    public int numSubleases = 0;
    public int numConnections = 0;
    public double rating = 0;
    public String imageURL = "";
    public String numBedroomRange = "";
    public boolean parking = false;
    public boolean pets = false;
    public ArrayList<Review> reviews;
    public ArrayList<Sublease> subleases;
    public String priceRange = "";

    public Building(int id, String address, String numBedroomRange, boolean parking, boolean pets, String imageURL) {
        this.id = id;
        this.address = address;
        this.numBedroomRange = numBedroomRange;
        this.parking = parking;
        this.pets = pets;
        this.imageURL = imageURL;
        reviews = new ArrayList<>();
        subleases = new ArrayList<>();
    }
}
