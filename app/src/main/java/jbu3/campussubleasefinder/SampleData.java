package jbu3.campussubleasefinder;

import java.util.ArrayList;

import jbu3.campussubleasefinder.models.Building;
import jbu3.campussubleasefinder.models.Review;
import jbu3.campussubleasefinder.models.Sublease;
import jbu3.campussubleasefinder.models.User;

public class SampleData {
    public static ArrayList<Building> buildings = new ArrayList<Building>() {{
        add(new Building("123 Example St.", 4, 2, 4.5, "https://s.realpage.com/wp-content/uploads/sites/20/2016/02/shutterstock_135206831-1-e1565815548959.jpg"));
        add(new Building("123 Exampfjshkfle St.", 5, 1, 3.5, "https://cdngeneral.rentcafe.com/dmslivecafe/3/632714/exterior-san-antonio-apartments.jpg"));
    }};

    public static ArrayList<Sublease> subleases = new ArrayList<Sublease>() {{
        add(new Sublease(0, 0, 430, "1/20","6/11", 3, 2, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."));
    }};

    public static ArrayList<User> users = new ArrayList<User>() {{
        add(new User("Jane Doe", 3, 4.5));
        add(new User("John Doe", 5, 3.5));
    }};

    public static ArrayList<Review> reviews = new ArrayList<Review>() {{
        add(new Review(0, 0, 4.5, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et"));
        add(new Review(1, 1, 2.5, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et"));
    }};
}
