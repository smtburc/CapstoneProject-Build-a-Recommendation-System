package filters;

import database.MovieDatabase;

public class MinutesFilter implements Filter {

    int minimum;
    int maximum;

    public MinutesFilter(int minimum,int maximum){
        this.minimum=minimum;
        this.maximum=maximum;
    }

    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getMinutes(id)>=minimum && MovieDatabase.getMinutes(id)<=maximum;
    }
}
