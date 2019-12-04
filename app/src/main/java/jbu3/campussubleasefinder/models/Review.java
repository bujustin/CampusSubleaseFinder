package jbu3.campussubleasefinder.models;

public class Review {
    public int userID;
    public int buildingID;
    public double rating;
    public String text;

    public Review(int userID, int buildingID, double rating, String text) {
        this.userID = userID;
        this.buildingID = buildingID;
        this.rating = rating;
        this.text = text;
    }
}
