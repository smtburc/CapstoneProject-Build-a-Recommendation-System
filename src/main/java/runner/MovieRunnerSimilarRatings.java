package runner;

import database.MovieDatabase;
import filters.*;
import pojo.Rating;
import ratings.FourthRatings;
import ratings.ThirdRatings;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {

    public void printAverageRatings() {

        FourthRatings fourthRatings = new FourthRatings();
        ArrayList<Rating> ratings = fourthRatings.getAverageRatings(35);
        Collections.sort(ratings);

        print(fourthRatings, ratings);
    }

    public void printAverageRatingsByYearAfterAndGenre() {

        FourthRatings fourthRatings = new FourthRatings();
        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(new YearAfterFilter(1990));
        allFilter.addFilter(new GenreFilter("Drama"));
        ArrayList<Rating> ratings = fourthRatings.getAverageRatingsByFilter(8, allFilter);
        Collections.sort(ratings);

        print(fourthRatings, ratings);
    }

    public void printSimilarRatings(){
        FourthRatings fourthRatings = new FourthRatings();
        ArrayList<Rating> ratings = fourthRatings.getSimilarRatings("65",20,5);


        print(fourthRatings, ratings);
    }

    public void printSimilarRatingsByGenre (){
        FourthRatings fourthRatings = new FourthRatings();
        ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter("65",20,5,new GenreFilter("Action"));


        print(fourthRatings, ratings);
    }

    public void printSimilarRatingsByDirector  (){
        FourthRatings fourthRatings = new FourthRatings();
        ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter("1034",10,3,new DirectorsFilter("Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone"));


        print(fourthRatings, ratings);
    }

    public void printSimilarRatingsByGenreAndMinutes   (){
        FourthRatings fourthRatings = new FourthRatings();
        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(new MinutesFilter(100,200));
        allFilter.addFilter(new GenreFilter("Adventure"));
        ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter("65",10,5,allFilter);


        print(fourthRatings, ratings);
    }

    public void printSimilarRatingsByYearAfterAndMinutes (){
        FourthRatings fourthRatings = new FourthRatings();
        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(new MinutesFilter(80,100));
        allFilter.addFilter(new YearAfterFilter(2000));
        ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter("65",10,5,allFilter);


        print(fourthRatings, ratings);
    }

    private void print(FourthRatings fourthRatings, ArrayList<Rating> ratings) {
        System.out.println("read data for " + fourthRatings.getRaterSize() + " raters");
        System.out.println("read data for " + fourthRatings.getMovieSize() + " movies");
        System.out.println("found " + ratings.size() + " movies");
        for (Rating r : ratings) {
            System.out.println(r.getValue() + " " + fourthRatings.getTitle(r.getItem()));
            System.out.println("   Genres: " + MovieDatabase.getGenres(r.getItem()));
            System.out.println("   Time: " + MovieDatabase.getMinutes(r.getItem()));
            System.out.println("   Director: " + MovieDatabase.getDirector(r.getItem()));
        }
    }


}
