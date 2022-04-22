import java.util.Comparator;

public class SortPointsByY implements Comparator<Point> {
    @Override
    public int compare(Point pt1, Point pt2) {
        return Double.compare(pt1.getY(), pt2.getY());
    }
}
