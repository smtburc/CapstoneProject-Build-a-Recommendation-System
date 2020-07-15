package pojo;

import lombok.Getter;

// An immutable passive data object (PDO) to represent the rating data
public class Rating implements Comparable<Rating> {
    private @Getter String item;
    private @Getter double value;

    public Rating(String anItem, double aValue) {
        item = anItem;
        value = aValue;
    }

    // Returns a string of all the rating information
    public String toString () {
        return "[" + getItem() + ", " + getValue() + "]";
    }

    public int compareTo(Rating other) {
        if (value < other.value) return -1;
        if (value > other.value) return 1;
        
        return 0;
    }
}
