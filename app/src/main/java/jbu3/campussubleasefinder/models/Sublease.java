package jbu3.campussubleasefinder.models;

public class Sublease {
    public int id;
    public int sublessorID;
    public int buildingID;
    public int price;
    public String startDate;
    public String endDate;
    public int numBeds;
    public int numBaths;
    public String details;

    public Sublease(int id, int sublessorID, int buildingID, int price, String startDate, String endDate, int numBeds, int numBaths, String details) {
        this.id = id;
        this.sublessorID = sublessorID;
        this.buildingID = buildingID;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numBaths = numBaths;
        this.numBeds = numBeds;
        this.details = details;
    }
}
