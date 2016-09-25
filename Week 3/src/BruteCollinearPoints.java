import java.util.Arrays;
import java.util.ArrayList;

public class BruteCollinearPoints {

    private ArrayList<LineSegment> segments = new ArrayList<>();

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }

        Point[] clone = points.clone();
        Arrays.sort(clone);

        for (int i = 0; i < clone.length - 1; i++) {
            if (clone[i].compareTo(clone[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        for (int i = 0; i < clone.length - 3; i++) {
            for (int j = i + 1; j < clone.length - 2; j++) {
                double slope1 = clone[i].slopeTo(clone[j]);
                for (int k = j + 1; k < clone.length - 1; k++) {
                    double slope2 = clone[i].slopeTo(clone[k]);
                    if (slope1 == slope2) {
                        for (int l = k + 1; l < clone.length; l++) {
                            double slope3 = clone[i].slopeTo(clone[l]);
                            if (slope1 == slope3) {
                                segments.add(new LineSegment(clone[i], clone[l]));
                            }
                        }
                    }
                }

            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    }
}