/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 *
 * @author Samet Burç
 */

import org.junit.Before;
import org.junit.Test;
import pojo.Movie;
import pojo.Rater;
import ratings.FirstRatings;

import java.util.*;
import java.util.stream.Collectors;

public class FirstRatingsTest {


    FirstRatings firstRatings;

    @Before
    public void init(){
        firstRatings=new FirstRatings();
    }

    @Test
    public void testLoadRaters() throws Exception {
        List<Rater> raterList = firstRatings.loadRaters("ratings.csv");
        System.out.println("total size " + raterList.size());
        Optional<Rater> size = raterList.stream().filter(x -> x.getID().equals("193")).findFirst();
        if (size.isPresent()) {
            System.out.println("rater_id is 193 count " + size.get().getItemsRated().size());
        }
        int max=0;
        String maxRaterId="";
        for(Rater rater:raterList){
            if(rater.getItemsRated().size()>max){
                max=rater.getItemsRated().size();
                maxRaterId=rater.getID();
            }
        }
        System.out.println("max "+max);
        System.out.println("maxRaterId "+maxRaterId);
        Map<String, Set<Rater>> movieMap=new HashMap<>();
        for(Rater rater:raterList){
            for(String movieID:rater.getItemsRated()){
                if(!movieMap.keySet().contains(movieID)){
                    Set<Rater> tempList=new HashSet<>();
                    tempList.add(rater);
                    movieMap.put(movieID,tempList);
                }else{
                    movieMap.get(movieID).add(rater);
                }
            }
        }


        System.out.println("1798709 movie rated counts "+movieMap.get("1798709").size());
        System.out.println("how many different movies "+movieMap.keySet().size());
    }

    @Test
    public void testLoadMovies() throws Exception {
        List<Movie> movieList = firstRatings.loadMovies("ratedmoviesfull.csv");
        System.out.println("total film " + movieList.size());
        System.out.println("genres comedy " + movieList.parallelStream().filter(x -> x.getGenres().contains("Comedy")).collect(Collectors.toList()).size());
        System.out.println("150min+ " + movieList.parallelStream().filter(x -> x.getMinutes() > 150).collect(Collectors.toList()).size());
        Map<String, Set<Movie>> directorMap = new HashMap<>();
        for (Movie movie : movieList) {
            String[] directors = movie.getDirector().split(",");
            for (String s : directors) {
                String director = s.trim();
                if (!directorMap.keySet().contains(director)) {
                    Set<Movie> tempList = new HashSet<>();
                    tempList.add(movie);
                    directorMap.put(director, tempList);
                } else {
                    directorMap.get(director).add(movie);
                }
            }
        }
        int max = 0;
        String maxDirector="";
        for (String key : directorMap.keySet()) {
            if (directorMap.get(key).size() > max) {
                max = directorMap.get(key).size();
                maxDirector=key;
            }
        }
        final int maxVal = max;
        System.out.println("maximum film of directors " + max);
        System.out.println("max director " + maxDirector);
    }
}
