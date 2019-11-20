package jbu3.campussubleasefinder.models;

public class Review {
    public int userIdx;
    public int buildingIdx;
    public double rating;
    public String text;

    public Review(int userIdx, int buildingIdx, double rating, String text) {
        this.userIdx = userIdx;
        this.buildingIdx = buildingIdx;
        this.rating = rating;
        this.text = text;
    }
}
