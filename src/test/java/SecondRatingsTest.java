/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 *
 * @author Samet Burç
 */

import org.junit.Before;
import org.junit.Test;
import pojo.Rating;
import ratings.SecondRatings;

import java.util.*;

public class SecondRatingsTest {

    SecondRatings secondRatings;

    @Before
    public void init(){
        secondRatings=new SecondRatings("ratedmovies_short.csv","ratings_short.csv");
    }

    @Test
    public void testPrintAverageRatings(){
        ArrayList<Rating> ratings= secondRatings.getAverageRatings(1);
        Collections.sort(ratings);
        for (Rating r:ratings) {
            System.out.println(r.getValue()+" " + secondRatings.getTitle(r.getItem()));
        }
    }

    @Test
    public void testGetAverageRatingOneMovie(){
        String id=secondRatings.getID("The Godfather");
        Optional<Rating> rating=secondRatings.getAverageRatings(0).stream().filter(x->x.getItem().equals(id)).findFirst();
        if(rating.isPresent()){
            System.out.println(rating.get().getValue());
        }
    }
}
