package pojo;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class EfficientRater implements Rater {
    private String myID;
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item,new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        return myRatings.keySet().contains(item);
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        if(myRatings.containsKey(item)){
            return myRatings.get(item).getValue();
        }
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        return (ArrayList<String>) myRatings.keySet().stream().collect(Collectors.toList());
    }
}
