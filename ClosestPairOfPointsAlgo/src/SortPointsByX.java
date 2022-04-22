import java.util.Comparator;

public class SortPointsByX implements Comparator<Point> {

    @Override
    public int compare(Point pt1, Point pt2) {
        return Double.compare(pt1.getX(), pt2.getX());
    }
}
