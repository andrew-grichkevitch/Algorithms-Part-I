import java.util.Arrays;
import java.util.ArrayList;

public class FastCollinearPoints {

    private ArrayList<LineSegment> segments = new ArrayList<>();

    // finds all line segments containing 4 points
    public FastCollinearPoints(Point[] points) {
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
            Arrays.sort(clone);
            Arrays.sort(clone, clone[i].slopeOrder());

            for (int j = 0, first = 1, last = 2; last < clone.length; last++) {
                while (last < clone.length && clone[j].slopeTo(clone[first]) == clone[j].slopeTo(clone[last])) {
                    last++;
                }

                if (last - first >= 3 && clone[j].compareTo(clone[first]) < 0) {
                    segments.add(new LineSegment(clone[j], clone[last - 1]));
                }

                first = last;
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