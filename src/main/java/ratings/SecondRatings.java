package ratings;



import pojo.Movie;
import pojo.Rater;
import pojo.Rating;

import java.util.ArrayList;
import java.util.Optional;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }

    public SecondRatings(String movieFileName, String ratingFileName) {
        try {
            myMovies =  new FirstRatings().loadMovies(movieFileName);
            myRaters =  new FirstRatings().loadRaters(ratingFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getMovieSize(){
        return myMovies.size();
    }

    public int getRaterSize(){
        return myRaters.size();
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

    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> result=new ArrayList<>();
        for(Movie movie:myMovies){
            double rating=getAverageByID(movie.getId(),minimalRaters);
            if(rating>0.0){
                result.add(new Rating(movie.getId(),rating));
            }
        }
        return result;
    }

    public String getTitle(String id){
        Optional<Movie> movie=myMovies.stream().filter(x->x.getId().equals(id)).findFirst();
        if(movie.isPresent()){
            return movie.get().getTitle();
        }
        return "ID was not found";
    }

    public String getID(String title ){
        Optional<Movie> movie=myMovies.stream().filter(x->x.getTitle().equals(title)).findFirst();
        if(movie.isPresent()){
            return movie.get().getId();
        }
        return "NO SUCH TITLE.";
    }

}