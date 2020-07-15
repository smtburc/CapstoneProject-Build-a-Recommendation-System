package filters;

import database.MovieDatabase;

public class DirectorsFilter implements Filter {

    String[] name;

    public DirectorsFilter(String name){
        this.name=name.split(",");
    }

    @Override
    public boolean satisfies(String id) {
        for(String director:name){
            if(MovieDatabase.getDirector(id).contains(director)){
                return true;
            }
        }
        return false;
    }
}
