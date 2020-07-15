package pojo;

import lombok.Getter;

// An immutable passive data object (PDO) to represent item data
public class Movie {
    private @Getter String id;
    private @Getter String title;
    private @Getter int year;
    private @Getter String genres;
    private @Getter String director;
    private @Getter String country;
    private @Getter int minutes;
    private @Getter String poster;

    public Movie(String anID, String aTitle, String aYear, String theGenres) {
        // just in case data file contains extra whitespace
        id = anID.trim();
        title = aTitle.trim();
        year = Integer.parseInt(aYear.trim());
        genres = theGenres;
    }

    public Movie(String anID, String aTitle, String aYear, String theGenres, String aDirector,
                 String aCountry, String aPoster, int theMinutes) {
        // just in case data file contains extra whitespace
        id = anID.trim();
        title = aTitle.trim();
        year = Integer.parseInt(aYear.trim());
        genres = theGenres;
        director = aDirector;
        country = aCountry;
        poster = aPoster;
        minutes = theMinutes;
    }

    // Returns a string of the item's information
    public String toString () {
        String result = "Movie [id=" + id + ", title=" + title + ", year=" + year;
        result += ", genres= " + genres + "]";
        return result;
    }
}
