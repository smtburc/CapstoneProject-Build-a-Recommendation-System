package runner;

import database.MovieDatabase;
import filters.*;
import pojo.Rating;
import ratings.ThirdRatings;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {


    public MovieRunnerWithFilters() {
    }

    public void printAverageRatings() {
//        ThirdRatings secondRatings = new ThirdRatings("ratings_short.csv");
        ThirdRatings secondRatings = new ThirdRatings();
        ArrayList<Rating> ratings = secondRatings.getAverageRatings(35);
        Collections.sort(ratings);

        print(secondRatings, ratings);
    }

    public void printAverageRatingsByYearAfter () {
//        ThirdRatings secondRatings = new ThirdRatings("ratings_short.csv");
        ThirdRatings secondRatings = new ThirdRatings();
        ArrayList<Rating> ratings = secondRatings.getAverageRatingsByFilter(20, new YearAfterFilter(2000));
        Collections.sort(ratings);

        print(secondRatings, ratings);
    }


    public void printAverageRatingsByGenre() {
//        ThirdRatings secondRatings = new ThirdRatings("ratings_short.csv");
        ThirdRatings secondRatings = new ThirdRatings();
        ArrayList<Rating> ratings = secondRatings.getAverageRatingsByFilter(20, new GenreFilter("Comedy"));
        Collections.sort(ratings);

        print(secondRatings, ratings);
    }

    public void printAverageRatingsByMinutes() {
//        ThirdRatings secondRatings = new ThirdRatings("ratings_short.csv");
        ThirdRatings secondRatings = new ThirdRatings();
        ArrayList<Rating> ratings = secondRatings.getAverageRatingsByFilter(5, new MinutesFilter(105, 135));
        Collections.sort(ratings);

        print(secondRatings, ratings);
    }

    public void printAverageRatingsByDirectors() {
//        ThirdRatings secondRatings = new ThirdRatings("ratings_short.csv");
        ThirdRatings secondRatings = new ThirdRatings();
        ArrayList<Rating> ratings = secondRatings.getAverageRatingsByFilter(4, new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"));
        Collections.sort(ratings);

        print(secondRatings, ratings);
    }

    public void printAverageRatingsByYearAfterAndGenre() {
//        ThirdRatings secondRatings = new ThirdRatings("ratings_short.csv");
        ThirdRatings secondRatings = new ThirdRatings();
        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(new YearAfterFilter(1990));
        allFilter.addFilter(new GenreFilter("Drama"));
        ArrayList<Rating> ratings = secondRatings.getAverageRatingsByFilter(8, allFilter);
        Collections.sort(ratings);

        print(secondRatings, ratings);
    }

    public void printAverageRatingsByDirectorsAndMinutes () {
//        ThirdRatings secondRatings = new ThirdRatings("ratings_short.csv");
        ThirdRatings secondRatings = new ThirdRatings();
        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        allFilter.addFilter(new MinutesFilter(90,180));
        ArrayList<Rating> ratings = secondRatings.getAverageRatingsByFilter(3, allFilter);
        Collections.sort(ratings);

        print(secondRatings, ratings);
    }


    private void print(ThirdRatings thirdRatings, ArrayList<Rating> ratings) {
        System.out.println("read data for " + thirdRatings.getRaterSize() + " raters");
        System.out.println("read data for " + thirdRatings.getMovieSize() + " movies");
        System.out.println("found " + ratings.size() + " movies");
        for (Rating r : ratings) {
            System.out.println(r.getValue() + " " + thirdRatings.getTitle(r.getItem()));
            System.out.println("   Genres: " + MovieDatabase.getGenres(r.getItem()));
            System.out.println("   Time: " + MovieDatabase.getMinutes(r.getItem()));
            System.out.println("   Director: " + MovieDatabase.getDirector(r.getItem()));
        }
    }
}
