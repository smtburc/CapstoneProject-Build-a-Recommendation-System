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
import ratings.ThirdRatings;

import java.util.ArrayList;
import java.util.Collections;

public class ThirdRatingsTest {

    ThirdRatings thirdRatings;

    @Before
    public void init(){
        thirdRatings=new ThirdRatings("ratings_short.csv");
    }

    @Test
    public void testPrintAverageRatings() {

        ArrayList<Rating> ratings = thirdRatings.getAverageRatings(1);
        Collections.sort(ratings);

        print(thirdRatings, ratings);
    }

    @Test
    public void testPrintAverageRatingsByYearAfter () {

        ArrayList<Rating> ratings = thirdRatings.getAverageRatingsByFilter(1, new YearAfterFilter(2000));
        Collections.sort(ratings);

        print(thirdRatings, ratings);
    }

    @Test
    public void testPrintAverageRatingsByGenre() {

        ArrayList<Rating> ratings = thirdRatings.getAverageRatingsByFilter(1, new GenreFilter("Comedy"));
        Collections.sort(ratings);

        print(thirdRatings, ratings);
    }

    @Test
    public void testPrintAverageRatingsByMinutes() {

        ArrayList<Rating> ratings = thirdRatings.getAverageRatingsByFilter(1, new MinutesFilter(105, 135));
        Collections.sort(ratings);

        print(thirdRatings, ratings);
    }

    @Test
    public void testPrintAverageRatingsByDirectors() {

        ArrayList<Rating> ratings = thirdRatings.getAverageRatingsByFilter(1, new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"));
        Collections.sort(ratings);

        print(thirdRatings, ratings);
    }

    @Test
    public void testPrintAverageRatingsByYearAfterAndGenre() {

        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(new YearAfterFilter(1990));
        allFilter.addFilter(new GenreFilter("Drama"));
        ArrayList<Rating> ratings = thirdRatings.getAverageRatingsByFilter(1, allFilter);
        Collections.sort(ratings);

        print(thirdRatings, ratings);
    }

    @Test
    public void testPrintAverageRatingsByDirectorsAndMinutes () {

        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        allFilter.addFilter(new MinutesFilter(90,180));
        ArrayList<Rating> ratings = thirdRatings.getAverageRatingsByFilter(1, allFilter);
        Collections.sort(ratings);

        print(thirdRatings, ratings);
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
