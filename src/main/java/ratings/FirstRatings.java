package ratings;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import pojo.EfficientRater;
import pojo.Movie;
import pojo.Rater;

import java.util.*;
import java.util.stream.Collectors;

public class FirstRatings {

    String resourcesPath=System.getProperty("user.dir") + "\\src\\main\\resources\\data\\";


    public ArrayList<Movie> loadMovies(String filename) throws Exception {
        FileResource fileResource = new FileResource(resourcesPath + filename);
        CSVParser csvParser = fileResource.getCSVParser();
        ArrayList<Movie> movieList = new ArrayList<>();
        for (CSVRecord record : csvParser.getRecords()) {
            Movie movie = new Movie(record.get("id"), record.get("title"), record.get("year"), record.get("genre"), record.get("director"), record.get("country"), record.get("poster"), Integer.parseInt(record.get("minutes")));
            movieList.add(movie);
        }
        return movieList;
    }

    public ArrayList<Rater> loadRaters(String filename) throws Exception {
        FileResource fileResource = new FileResource(resourcesPath + filename);
        CSVParser csvParser = fileResource.getCSVParser();
        Map<String, Rater> raterMap = new HashMap<>();
        for (CSVRecord record : csvParser.getRecords()) {
            String raterID = record.get("rater_id");
            if (!raterMap.keySet().contains(raterID)) {
                Rater rater = new EfficientRater(raterID);
                rater.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                raterMap.put(raterID, rater);
            } else {
                raterMap.get(raterID).addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
            }
        }
        return (ArrayList)raterMap.values().stream().collect(Collectors.toList());
    }



}
