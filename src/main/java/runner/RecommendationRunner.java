package runner;

import database.MovieDatabase;
import database.RaterDatabase;
import filters.TrueFilter;
import pojo.Movie;
import pojo.Rater;
import pojo.Rating;
import ratings.FourthRatings;

import java.util.ArrayList;
import java.util.Random;

public class RecommendationRunner implements Recommender {

    @Override
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> movieList= MovieDatabase.filterBy(new TrueFilter());
        ArrayList<String> result=new ArrayList<>();
        Random random=new Random();
        while(result.size()<20 && movieList.size()>0){
            int index=random.nextInt(movieList.size());
            result.add(movieList.get(index));
            movieList.remove(index);
        }
        return result;
    }

    @Override
    public void printRecommendationsFor(String webRaterID) {
        Rater rater=RaterDatabase.getRater(webRaterID);
        ArrayList<String> recomendList=new ArrayList<>();

        FourthRatings fourthRatings=new FourthRatings();
        ArrayList<Rating> similarMovies= fourthRatings.getSimilarRatings(webRaterID,30,10);

        for(Rating rating:similarMovies){
            if(!rater.hasRating(rating.getItem()) && !recomendList.contains(rating.getItem()) && recomendList.size()<10){
                recomendList.add(rating.getItem());
            }
        }

        ArrayList<Rating> averageMovies= fourthRatings.getAverageRatings(10);

        for(Rating rating:averageMovies){
            if(!rater.hasRating(rating.getItem()) && !recomendList.contains(rating.getItem()) && recomendList.size()<10){
                recomendList.add(rating.getItem());
            }
        }

        if(recomendList.isEmpty()){
            System.out.println("Movie not found for recommend");
            return;
        }

        System.out.println("<table>");

        System.out.println("<th>Poster</th><th>Title</th><th>Genres</th><th>Year</th><th>Minutes</th><th>Country</th><th>Director</th>");
        for(String movieID:recomendList){
            Movie movie=MovieDatabase.getMovie(movieID);
            System.out.println("<tr>");

            System.out.println("<td><img src=\"" + movie.getPoster() + "\"></td>");
            System.out.println("<td>"+movie.getTitle()+"</td>");
            System.out.println("<td>"+movie.getGenres()+"</td>");
            System.out.println("<td>"+movie.getYear()+"</td>");
            System.out.println("<td>"+movie.getMinutes()+"</td>");
            System.out.println("<td>"+movie.getCountry()+"</td>");
            System.out.println("<td>"+movie.getDirector()+"</td>");

            System.out.println("</tr>");
        }
        System.out.println("</table>");

        System.out.println("<style>");
        System.out.println("img{\n" +
                "  height:150px;\n" +
                "}\n" +
                "td{\n" +
                " \n" +
                "    -webkit-tap-highlight-color: transparent;\n" +
                "    line-height: 1.5;\n" +
                "    padding: 1rem;\n" +
                "    background-color: #fff;\n" +
                "    border-bottom: 1px solid #ddd;\n" +
                "    text-align:center;\n" +
                "}\n" +
                "th{\n" +
                "  \n" +
                "    -webkit-tap-highlight-color: transparent;\n" +
                "    line-height: 1.5;\n" +
                "    padding: 1rem;\n" +
                "    background-color: #fff;\n" +
                "    border-bottom: 1px solid #ddd;\n" +
                "    min-width:150px;\n" +
                "}");
        System.out.println("</style>");
    }


}
