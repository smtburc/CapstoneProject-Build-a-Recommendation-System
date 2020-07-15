package filters;

import database.MovieDatabase;

public class GenreFilter implements Filter {

    String genre;

    public GenreFilter(String genre){
        this.genre=genre;
    }

    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getGenres(id).contains(genre);
    }
}
