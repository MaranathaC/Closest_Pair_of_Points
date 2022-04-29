import java.util.Comparator;

/**
 * Comparator for points
 * Used for Arrays.sort()
 * @author Natha Chiu
 */
public class SortPointsByY implements Comparator<Point> {
    /**
     * compare
     * pre: points have been allocated memory
     * post: compare using y coordinates; return -1, 0, 1 (<, ==, >)
     */
    @Override
    public int compare(Point pt1, Point pt2) {
        return Double.compare(pt1.getY(), pt2.getY());
    }
}
