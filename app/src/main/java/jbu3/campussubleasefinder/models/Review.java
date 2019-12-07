package jbu3.campussubleasefinder.models;

public class Review {
    public int sublessorID;
    public int sublesseeID;
    public int buildingID;
    public double rating;
    public String text;

    public Review(int sublessorID, int sublesseeID, int buildingID, double rating, String text) {
        this.sublessorID = sublessorID;
        this.sublesseeID = sublesseeID;
        this.buildingID = buildingID;
        this.rating = rating;
        this.text = text;
    }
}
