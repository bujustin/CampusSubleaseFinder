package jbu3.campussubleasefinder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
                        if (SampleData.isConnection(sublease.sublessorID)) {
                            building.numConnections++;
                        }
                        if (sublease.price > filters.minPrice && (filters.maxPrice < 0 || sublease.price < filters.maxPrice) && (filters.bed < 0 || sublease.numBeds == filters.bed) && (filters.bath < 0 || sublease.numBaths == filters.bath) && (!filters.connection || isConnection(sublease.sublessorID))) {
                            SimpleDateFormat format = new SimpleDateFormat("M/d/yy");
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

                Collections.sort(building.subleases, new Comparator<Sublease>() {
                    @Override
                    public int compare(Sublease o1, Sublease o2) {
                        return Integer.compare(o1.price, o2.price);
                    }
                });

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

        Collections.sort(filteredBuildings, new Comparator<Building>() {
            @Override
            public int compare(Building o1, Building o2) {
                int c = Integer.compare(o1.numConnections, o2.numConnections);
                if (c == 0)
                    c = Double.compare(o1.rating, o2.rating);
                return -c;
            }
        });
    }

    public static Building findBuildingByID(int id) {
        for (Building buildingIn: filteredBuildings) {
            if (buildingIn.id == id) {
                return buildingIn;
            }
        }

        return null;
    }

    public static ArrayList<Building> buildings = new ArrayList<Building>() {{
        add(new Building(0,"309 E Green St.",  "2-4", true, false, "https://upload.wikimedia.org/wikipedia/commons/d/dc/309_Green_Street_Champaign.jpg"));
        add(new Building(1,"519 E Green St. (Skyline Apartments)", "2-4", false, true,"https://www.bankierapartments.com/uploads/application/images/1000/dmam6oyl1xnm7ok0mtihaylhc2vd5oqdrrtnof787kp78lsob6.jpg"));
        add(new Building(2,"501 S 6th St.",  "2-5", true, false, "https://photonet.hotpads.com/search/listingPhoto/Postlets/RT15712810/0000_978452556_large.jpg"));
        add(new Building(3,"308 E Green St. (HERE)",  "3-4", true, true, "https://commoncdn.entrata.com/images/thumbNailer.php?src=/media_library/10364/564a62b3cefb6809.jpg&w=1280&h=720"));
        add(new Building(4,"608 E University Ave. (Latitude Apartments)",  "2-4", false, false, "https://bloximages.newyork1.vip.townnews.com/news-gazette.com/content/tncms/assets/v3/editorial/3/2f/32f21134-c2c5-5131-becb-6cb88e4f9f3b/5d137115ae39a.image.jpg?resize=1700%2C1133"));
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
        add(new Sublease(5,1, 4, 120,
                "8/14/20","12/12/20", 4, 2,
                "Close to Siebel Center and the Engineering Quad."));
        add(new Sublease(6,2, 3, 750,
                "7/20/20","11/20/20", 4, 2,
                "Nice house. Big rooms. Big doors. Very good."));
        add(new Sublease(7,3, 3, 250,
                "10/01/20","12/12/20", 4, 2,
                "Close to my favorite restaurant pizza hut."));
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

    public static boolean isConnection(int otherUserID) {
        return Arrays.asList(users.get(0).connectionIDs).contains(otherUserID);
    }

    public static Integer findNumConnections(int user1Id, int user2Id) {
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
                        if (review.sublessorID == user.id) {
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
        add(new Review(1, 2, 0, 4.5, "Great place to stay! I could walk to class everyday which was great as I don't have a car. The Host was very courteous. It was great experience staying here!!"));
        add(new Review(2, 3, 0, 2.5, "The building was not as advertised by the host. The apartment was very dirty and some of facilities promised were not working. The building was nice but I do not recommend staying here."));
        add(new Review(3, 4, 0, 4.5, "Fantastic Experience!! Building has great facilities and the apartment comes with a balcony that provides a great panoramic view of the city. The Host was great and upfront with all our dealings."));
        add(new Review(4, 1, 1, 5, "Great place to stay. I would definitely recommend staying here if you are taking classes near the main quad. The sublessor was very nice and helped me settle in with the other roommates."));
        add(new Review(2, 0, 1, 4.5, "Love the building !! Love the Sublessor !! Fantastic Experience !! 100% recommend !!"));
        add(new Review(0, 4, 2, 5, "Awesome Experience! The roommates were very friendly. The location was great – right next to the bars!!. The Apartment was new and clean with great amenities. The sublessor was very helpful. I would recommend this to anyone who likes going to bars and wants to live in a modern building - full of college students."));
        add(new Review(4, 2, 2, 4.5, "Perfect Location – 10 min walk to the main quad, near a lot of the restaurants on campus and has a couple of bars nearby. Apartment was clean and has great maintenance. The host was very adjusting and helpful!!"));
        add(new Review(3, 1, 3, 3.5, "DO NOT STAY HERE! The sublessor was great and very helpful but the building staff/maintenance were horrible. Took them 3 weeks to fix the air conditioning. Also, a lot of the amenities are very old and don’t work properly."));
        add(new Review(1, 3, 4, 4.5, "Loved the building but the staff and the building managers were very rude.I was overcharged for utilities multiple times.The sublessor was nice and helped me out the best he could.Almost the perfect experience!!"));
        add(new Review(0, 2, 4, 2.5, "Filthy Apartment!! There was mold in the fridge which had not been cleaned in a while. The room was very small and didn’t have enough space for my stuff.The host was not upfront about the state of the apartment."));

    }};

//    public static Review findUserReviews(int userId, int buildingId) {
//        for (Review review : reviews) {
//            if (review.userID == userId && review.buildingID == buildingId) {
//                return review;
//            }
//        }
//        return null;
//    }
}
