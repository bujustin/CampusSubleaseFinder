package jbu3.campussubleasefinder.models;

public class Sublease {
    public int sublessorIdx;
    public int buildingIdx;
    public int price;
    public String startDate;
    public String endDate;
    public int numBeds;
    public int numBaths;
    public String details;

    public Sublease(int sublessorIdx, int buildingIdx, int price, String startDate, String endDate, int numBeds, int numBaths, String details) {
        this.sublessorIdx = sublessorIdx;
        this.buildingIdx = buildingIdx;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numBaths = numBaths;
        this.numBeds = numBeds;
        this.details = details;
    }
}
