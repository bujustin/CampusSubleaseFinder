package jbu3.campussubleasefinder.models;

import java.util.Date;

public class FilterData {
    public String address = "";
    public Date startDate;
    public Date endDate;
    public int minPrice = -1;
    public int maxPrice = -1;
    public int bed = -1;
    public int bath = -1;
    public double rating = -1;
    public boolean parking = false;
    public boolean pets = false;

    public FilterData() { }
    public FilterData(String address, Date startDate, Date endDate, int minPrice, int maxPrice, int bed, int bath, double rating, boolean parking, boolean pets) {
        this.address = address;
        this.startDate = startDate;
        this.endDate = endDate;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.bed = bed;
        this.bath = bath;
        this.rating = rating;
        this.parking = parking;
        this.pets = pets;
    }
}
