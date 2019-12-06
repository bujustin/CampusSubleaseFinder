package jbu3.campussubleasefinder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import jbu3.campussubleasefinder.models.Building;
import jbu3.campussubleasefinder.models.FilterData;
import jbu3.campussubleasefinder.models.Review;
import jbu3.campussubleasefinder.models.Sublease;
import jbu3.campussubleasefinder.models.User;

public class SampleData {
    public static FilterData currentFilters = new FilterData();
    public static ArrayList<Building> filteredBuildings = new ArrayList<>();

    public static void setFilteredBuildings(FilterData filters) {
        currentFilters = filters;
        filteredBuildings.clear();
        for (Building building: buildings) {
            if ((filters.address.equals("") || building.address.equals(filters.address)) && building.rating >= filters.rating && (!filters.parking || building.parking) && (!filters.pets || building.pets)) {
                building.subleases.clear();
                building.reviews.clear();

                int maxBuildingPrice = 0;
                int minBuildingPrice = 100000000;
                building.numConnections = 0;
                building.numSubleases = 0;
                for (Sublease sublease : subleases) {
                    if (sublease.buildingID == building.id) {
                        building.numSubleases++;
                        if (sublease.price > maxBuildingPrice) {
                            maxBuildingPrice = sublease.price;
                        }
                        if (sublease.price < minBuildingPrice) {
                            minBuildingPrice = sublease.price;
                        }
                        if (Arrays.asList(users.get(0).connectionIDs).contains(sublease.sublessorID)) {
                            building.numConnections++;
                        }
                        if (sublease.price > filters.minPrice && (filters.maxPrice < 0 || sublease.price < filters.maxPrice) && (filters.bed < 0 || sublease.numBeds == filters.bed) && (filters.bath < 0 || sublease.numBaths == filters.bath)) {
                            SimpleDateFormat format = new SimpleDateFormat("mm/dd/yy");
                            try {
                                Date subStartDate = format.parse(sublease.startDate);
                                Date subEndDate = format.parse(sublease.endDate);
                                if ((filters.startDate == null || subStartDate == null || filters.startDate.after(subStartDate)) && (filters.endDate == null || subEndDate != null || subEndDate.after(filters.endDate))) {
                                    building.subleases.add(sublease);
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                if (maxBuildingPrice != 0) {
                    if (maxBuildingPrice != minBuildingPrice) {
                        building.priceRange = "$" + minBuildingPrice + " - $" + maxBuildingPrice;
                    } else {
                        building.priceRange = "$" + maxBuildingPrice;
                    }
                } else {
                    building.priceRange = "0";
                }

                building.rating = 0;
                for (Review review : reviews) {
                    if (review.buildingID == building.id) {
                        building.reviews.add(review);
                        building.rating += review.rating;
                    }
                }
                building.rating /= building.reviews.size();

                if (building.subleases.size() > 0) {
                    filteredBuildings.add(building);
                }
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
        add(new Building(0,"123 Example St.",  "2-4", true, false, "https://s.realpage.com/wp-content/uploads/sites/20/2016/02/shutterstock_135206831-1-e1565815548959.jpg"));
        add(new Building(1, "123 Exampfjshkfle St.", "2-3", false, true,"https://cdngeneral.rentcafe.com/dmslivecafe/3/632714/exterior-san-antonio-apartments.jpg"));
    }};

    public static ArrayList<Sublease> subleases = new ArrayList<Sublease>() {{
        add(new Sublease(0,0, 0, 430,
                "1/20/20","6/11/20", 3, 2,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."));
        add(new Sublease(1,1, 1, 630,
                "4/20/20","10/11/20", 1, 1,
                "This building is very nice. It has big doors and many windows!"));
    }};

    public static Sublease findSubleaseByID(int id) {
        for (Sublease sublease: subleases) {
            if (sublease.id == id) {
                return sublease;
            }
        }

        return null;
    }

    public static ArrayList<User> users = new ArrayList<User>() {{
        add(new User(0,"Jane Doe", "janed6@illinois.edu", "(324) 325-3463", "I have a cat", new Integer[]{1,2,4}));
        add(new User(1,"John Doe", "jognd18@illinois.edu",  "(645) 824-3857", "I am pretty cool", new Integer[]{0,4}));
        add(new User(2,"Patty Kake", "pk12@illinois.edu",  "(582) 145-2858", "I like big cakes", new Integer[]{0,3}));
        add(new User(3,"Gunther Smith", "gsmith@illinois.edu",  "(123) 623-2837", "I like 2 floor houses", new Integer[]{2}));
        add(new User(4,"Jake Blake", "jbl2@illinois.edu",  "(512) 623-5315", "I am pretty suave", new Integer[]{0,1}));
    }};

    public static Integer findConnections(int user1Id, int user2Id) {
        HashSet<Integer> set = new HashSet<>();

        set.addAll(Arrays.asList(findUserByID(user1Id, false).connectionIDs));
        set.retainAll(Arrays.asList(findUserByID(user2Id, false).connectionIDs));
        return set.size();
    }

    public static User findUserByID(int id, boolean populate) {
        for (User user: users) {
            if (user.id == id) {
                if (populate) {
                    user.reviews.clear();
                    user.subleases.clear();
                    user.connections.clear();
                    for (Sublease sublease : subleases) {
                        if (sublease.sublessorID == user.id) {
                            user.subleases.add(sublease);
                        }
                    }

                    user.rating = 0;
                    for (Review review : reviews) {
                        if (review.userID == user.id) {
                            user.rating += review.rating;
                            user.reviews.add(review);
                        }
                    }
                    user.rating /= user.reviews.size();

                    for (int connectionID : user.connectionIDs) {
                        user.connections.add(findUserByID(connectionID, false));
                    }
                }
                return user;
            }
        }

        return null;
    }

    public static ArrayList<Review> reviews = new ArrayList<Review>() {{
        add(new Review(0, 0, 4.5, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et"));
        add(new Review(1, 1, 2.5, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et"));
    }};

    public static Review findReview(int userId, int buildingId) {
        for (Review review : reviews) {
            if (review.userID == userId && review.buildingID == buildingId) {
                return review;
            }
        }

        return null;
    }
}
