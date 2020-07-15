package ratings;

import database.MovieDatabase;
import filters.Filter;
import filters.TrueFilter;
import pojo.Rater;
import pojo.Rating;

import java.util.ArrayList;
import java.util.Optional;

public class ThirdRatings {

    private ArrayList<Rater> myRaters;

    public ThirdRatings() {
        this("ratings.csv");
    }

    public ThirdRatings(String ratingsFileName) {
        try {
            myRaters = new FirstRatings().loadRaters(ratingsFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters){

        return getAverageRatingsByFilter(minimalRaters,new TrueFilter());
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters , Filter filterCriteria){

        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);

        ArrayList<Rating> result=new ArrayList<>();
        for(String movieID:movies){
            double rating=getAverageByID(movieID,minimalRaters);
            if(rating>0.0){
                result.add(new Rating(movieID,rating));
            }
        }
        return result;
    }

    private double getAverageByID(String id, int minimalRaters){

        ArrayList<Rater> raterForMovies=new ArrayList();
        for(Rater rater:myRaters) {
            for (String movieID : rater.getItemsRated()) {
                if(movieID.equals(id)){
                    raterForMovies.add(rater);
                }
            }
        }

        if(raterForMovies.size()<minimalRaters){
            return 0.0;
        }
        double score=0.0;
        for(Rater rater:raterForMovies){
            score += rater.getRating(id);
        }
        return score/raterForMovies.size();
    }

    public String getTitle(String id){
        return MovieDatabase.getTitle(id);
    }

    public int getRaterSize(){
        return myRaters.size();
    }


    public int getMovieSize(){
        return MovieDatabase.size();
    }


    public String getID(String title ){
        Optional<String> movie=MovieDatabase.filterBy(new TrueFilter()).stream().filter(x->MovieDatabase.getTitle(x).equals(title)).findFirst();;
        if(movie.isPresent()){
            return movie.get();
        }
        return "NO SUCH TITLE.";
    }


}
