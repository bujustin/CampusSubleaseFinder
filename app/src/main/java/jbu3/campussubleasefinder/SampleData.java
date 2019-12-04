package jbu3.campussubleasefinder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jbu3.campussubleasefinder.models.Building;
import jbu3.campussubleasefinder.models.Review;
import jbu3.campussubleasefinder.models.Sublease;
import jbu3.campussubleasefinder.models.User;

public class SampleData {
    public static ArrayList<Building> filteredBuildings = new ArrayList<>();

    public static void setFilteredBuildings(String address, Date startDate, Date endDate, int bed, int bath, int rating, boolean parking, boolean pets) {
        for (Building building: buildings) {
            if ((address == "" || building.address == address) && building.rating >= rating && (!parking || building.parking) && (!pets || building.pets)) {
                building.subleases.clear();
                building.reviews.clear();

                int maxPrice = 0;
                int minPrice = 100000000;
                for (Sublease sublease : subleases) {
                    if (sublease.buildingID == building.id && (bed > 0 && sublease.numBeds == bed) && (bath > 0 && sublease.numBaths == bath)) {
                        SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy");
                        try {
                            Date subStartDate = format.parse(sublease.startDate);
                            Date subEndDate = format.parse(sublease.endDate);
                            if (startDate == null || endDate == null || (subStartDate != null && subEndDate != null && startDate.after(subStartDate) && subEndDate.after(endDate))) {
                                building.subleases.add(sublease);

                                if (sublease.price > maxPrice) {
                                    maxPrice = sublease.price;
                                }

                                if (sublease.price < minPrice) {
                                    minPrice = sublease.price;
                                }
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }

                building.priceRange = minPrice + " - " + maxPrice;

                for (Review review : reviews) {
                    if (review.buildingID == building.id) {
                        building.reviews.add(review);
                    }
                }

                filteredBuildings.add(building);
            }
        }
    }

    public static Building findBuildingByID(int id) {
        for (Building buildingIn: buildings) {
            if (buildingIn.id == id) {
                return buildingIn;
            }
        }

        return null;
    }

    public static ArrayList<Building> buildings = new ArrayList<Building>() {{
        add(new Building(0,"123 Example St.", 4, 2, 4.5, "2-4", true, false, "https://s.realpage.com/wp-content/uploads/sites/20/2016/02/shutterstock_135206831-1-e1565815548959.jpg"));
        add(new Building(1, "123 Exampfjshkfle St.", 5, 1, 3.5, "2-3", false, true,"https://cdngeneral.rentcafe.com/dmslivecafe/3/632714/exterior-san-antonio-apartments.jpg"));
    }};

    public static ArrayList<Sublease> subleases = new ArrayList<Sublease>() {{
        add(new Sublease(0, 0, 430, "1/20/2020","6/11/2020", 3, 2, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."));
    }};

    public static ArrayList<User> users = new ArrayList<User>() {{
        add(new User(0,"Jane Doe", 3, 4.5));
        add(new User(1,"John Doe", 5, 3.5));
    }};

    public static ArrayList<Review> reviews = new ArrayList<Review>() {{
        add(new Review(0, 0, 4.5, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et"));
        add(new Review(1, 1, 2.5, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et"));
    }};
}
