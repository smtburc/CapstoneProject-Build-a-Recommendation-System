/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 *
 * @author Samet Burç
 */

import database.MovieDatabase;
import filters.*;
import org.junit.Before;
import org.junit.Test;
import pojo.Rating;
import ratings.FourthRatings;

import java.util.ArrayList;
import java.util.Collections;

public class FourthRatingsTest {

    FourthRatings fourthRatings;

    @Before
    public void init(){
        fourthRatings=new FourthRatings();
    }

    @Test
    public void printAverageRatings() {

        ArrayList<Rating> ratings = fourthRatings.getAverageRatings(35);
        Collections.sort(ratings);

        print(fourthRatings, ratings);
    }

    @Test
    public void printAverageRatingsByYearAfterAndGenre() {

        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(new YearAfterFilter(1990));
        allFilter.addFilter(new GenreFilter("Drama"));
        ArrayList<Rating> ratings = fourthRatings.getAverageRatingsByFilter(8, allFilter);
        Collections.sort(ratings);

        print(fourthRatings, ratings);
    }

    @Test
    public void printSimilarRatings(){

        ArrayList<Rating> ratings = fourthRatings.getSimilarRatings("71",20,5);

        print(fourthRatings, ratings);
    }

    @Test
    public void printSimilarRatingsByGenre (){

        ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter("964",20,5,new GenreFilter("Mystery"));

        print(fourthRatings, ratings);
    }

    @Test
    public void printSimilarRatingsByDirector  (){

        ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter("120",10,2,new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));

        print(fourthRatings, ratings);
    }

    @Test
    public void printSimilarRatingsByGenreAndMinutes   (){

        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(new MinutesFilter(80,160));
        allFilter.addFilter(new GenreFilter("Drama"));
        ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter("168",10,3,allFilter);

        print(fourthRatings, ratings);
    }

    @Test
    public void printSimilarRatingsByYearAfterAndMinutes (){

        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(new MinutesFilter(70,200));
        allFilter.addFilter(new YearAfterFilter(1975));
        ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter("314",10,5,allFilter);

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
