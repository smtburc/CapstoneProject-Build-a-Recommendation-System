package runner;


import pojo.Rating;
import ratings.SecondRatings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class MovieRunnerAverage {

    public void printAverageRatings(){
//        SecondRatings secondRatings=new SecondRatings("ratedmovies_short.csv","ratings_short.csv");
        SecondRatings secondRatings=new SecondRatings();
        ArrayList<Rating> ratings= secondRatings.getAverageRatings(12);
        Collections.sort(ratings);
        for (Rating r:ratings) {
            System.out.println(r.getValue()+" " + secondRatings.getTitle(r.getItem()));
        }
    }

    public void getAverageRatingOneMovie(){
//        SecondRatings secondRatings=new SecondRatings("ratedmovies_short.csv","ratings_short.csv");
        SecondRatings secondRatings=new SecondRatings();
        String id=secondRatings.getID("Vacation");
        Optional<Rating> rating=secondRatings.getAverageRatings(0).stream().filter(x->x.getItem().equals(id)).findFirst();
        if(rating.isPresent()){
            System.out.println(rating.get().getValue());
        }
    }


}
