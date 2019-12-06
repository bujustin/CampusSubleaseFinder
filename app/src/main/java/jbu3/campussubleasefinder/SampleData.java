package jbu3.campussubleasefinder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import jbu3.campussubleasefinder.models.Building;
import jbu3.campussubleasefinder.models.Review;
import jbu3.campussubleasefinder.models.Sublease;
import jbu3.campussubleasefinder.models.User;

public class SampleData {
    public static ArrayList<Building> filteredBuildings = new ArrayList<>();

    public static void setFilteredBuildings(String address, Date startDate, Date endDate, int bed, int bath, int rating, boolean parking, boolean pets) {
        filteredBuildings.clear();
        for (Building building: buildings) {
            if ((address == "" || building.address == address) && building.rating >= rating && (!parking || building.parking) && (!pets || building.pets)) {
                building.subleases.clear();
                building.reviews.clear();

                int maxPrice = 0;
                int minPrice = 100000000;
                for (Sublease sublease : subleases) {
                    if (sublease.buildingID == building.id && (bed < 0 || sublease.numBeds == bed) && (bath < 0 || sublease.numBaths == bath)) {
                        SimpleDateFormat format = new SimpleDateFormat("mm/dd/yy");
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

                if (maxPrice != 0) {
                    if (maxPrice != minPrice) {
                        building.priceRange = "$" + minPrice + " - $" + maxPrice;
                    } else {
                        building.priceRange = "$" + maxPrice;
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
        add(new Building(0,"309 E Green St.",  "2-4", true, false, "https://upload.wikimedia.org/wikipedia/commons/d/dc/309_Green_Street_Champaign.jpg"));
        add(new Building(1,"519 E Green St. (Skyline Apartments)", "2-4", true, true,"https://www.bankierapartments.com/uploads/application/images/1000/dmam6oyl1xnm7ok0mtihaylhc2vd5oqdrrtnof787kp78lsob6.jpg"));
        add(new Building(2,"501 S 6th St.",  "2-5", true, false, "https://photonet.hotpads.com/search/listingPhoto/Postlets/RT15712810/0000_978452556_large.jpg"));
        add(new Building(3,"308 E Green St. (HERE.)",  "2-4", true, false, "https://commoncdn.entrata.com/images/thumbNailer.php?src=/media_library/10364/564a62b3cefb6809.jpg&w=1280&h=720"));
        add(new Building(4,"608 E University Ave. (Latitude Apartments)",  "2-4", true, false, "https://bloximages.newyork1.vip.townnews.com/news-gazette.com/content/tncms/assets/v3/editorial/3/2f/32f21134-c2c5-5131-becb-6cb88e4f9f3b/5d137115ae39a.image.jpg?resize=1700%2C1133"));
    }};

    public static ArrayList<Sublease> subleases = new ArrayList<Sublease>() {{
        add(new Sublease(0,0, 0, 430,
                "1/20/20","6/11/20", 3, 2,
                "Has plenty of room inside of each bedroom. I have a high room and it has a great view outside."));
        add(new Sublease(1,1, 0, 530,
                "1/20/20","6/11/20", 3, 2,
                "Comes with a laundry room and has parking"));
        add(new Sublease(2,2, 1, 850,
                "4/20/20","10/11/20", 2, 1,
                "Its a great location, it's pretty close to campus and it's right next to the bars."));
        add(new Sublease(3,3, 2, 750,
                "8/20/20","12/20/20", 2, 1,
                "It's close to County Market.  Also not too far from the Quad"));
        add(new Sublease(4,4, 3, 550,
                "8/20/20","6/20/21", 4, 2,
                "Great apartment with 3 great roommates!"));
        add(new Sublease(5,5, 4, 550,
                "8/20/20","12/20/20", 4, 2,
                "Close to Siebel Center and the Engineering Quad."));
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
