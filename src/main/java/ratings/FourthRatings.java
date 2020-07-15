package ratings;

import database.MovieDatabase;
import database.RaterDatabase;
import filters.Filter;
import filters.TrueFilter;
import pojo.Rater;
import pojo.Rating;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class FourthRatings {


    public FourthRatings() {
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
        for(Rater rater:RaterDatabase.getRaters()) {
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
        return RaterDatabase.size();
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

    private double dotProduct(Rater me, Rater r) {
        ArrayList<String> items = MovieDatabase.filterBy(new TrueFilter());
        double product = 0.0;

        for (String item: items) {
            if (me.hasRating(item) && r.hasRating(item)) {
                double meScaledScore = me.getRating(item) - 5.0;
                double rScaledScore = r.getRating(item) - 5.0;
                double temp = meScaledScore * rScaledScore;
                product += temp;

            }
        }
        return product;
    }

    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> list = new ArrayList<>();
        Rater me = RaterDatabase.getRater(id);

        for (Rater rater: RaterDatabase.getRaters()) {
            double product = 0.0;
            if (!rater.getID().equals(me.getID())) {
                product = dotProduct(me,rater);
            }
            if (product > 0.0) {
                Rating rating = new Rating(rater.getID(), product);
                list.add(rating);
            }
        }
        Collections.sort(list, Collections.reverseOrder());

        return list;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {

        return getSimilarRatingsByFilter(id,numSimilarRaters,minimalRaters,new TrueFilter());
    }



    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters,Filter filterCriteria){
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> similarRaters = getSimilarities(id);
        ArrayList<Rater> raterList=new ArrayList<>();
        int i=0;
        while (raterList.size()<numSimilarRaters){
            raterList.add(RaterDatabase.getRater(similarRaters.get(i).getItem()));
            i++;
        }

        ArrayList<Rating> result=new ArrayList<>();
        for(String movieID:movies){
            double sum = 0.0;
            int count=0;
            for(Rater rater:raterList){
                if(rater.hasRating(movieID)){
                    double rating=rater.getRating(movieID)*similarRaters.get(raterList.indexOf(rater)).getValue();
                    sum += rating;
                    count++;
                }
            }
            if(count>=minimalRaters){
                result.add(new Rating(movieID,sum/count));
            }
        }

        Collections.sort(result,Collections.reverseOrder());
        return result;
    }

}
