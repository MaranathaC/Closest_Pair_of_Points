import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class ClosestPair {
    private Point[] sortedX;
    private Point[] sortedY;
    private Point[] closest;

    public ClosestPair() {
        Scanner sc;
        try {
            sc = new Scanner(new File("ClosestPairOfPointsAlgo/src/program2data.txt"));

            int size = sc.nextInt();
            sortedX = new Point[size];
            sortedY = new Point[size];
            closest = new Point[2];

            for(int i = 0; i < size; i++) {
                sortedX[i] = new Point(sc);
                sortedY[i] = sortedX[i];
            }

            Arrays.sort(sortedX, new SortPointsByX());
            Arrays.sort(sortedY, new SortPointsByY());

            for(int i = 0; i < size; i++) {
                sortedX[i].setIndex(i);
            }

            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void findPair() {
        closest = findPair(sortedX, sortedY, 0, sortedX.length - 1);
    }

    private Point[] findPair(Point[] sortedX, Point[] sortedY, int low, int high) {
        int numOfPoints = high - low + 1;

        Point[] pts = new Point[2];

        if(numOfPoints == 2) {
            pts[0] = sortedX[low];
            pts[1] = sortedX[high];
            return pts;
        }

        if(sortedX.length == 3) {
            pts = threePointsMin(sortedX[low], sortedX[low + 1], sortedX[high]);
            return pts;
        }

        int mid = low + (high - low) / 2;
        Point[] leftPair = findPair(sortedX, sortedY, low, mid);
        Point[] rightPair = findPair(sortedX, sortedY, mid + 1, high);

        double deltaLeft = distance(leftPair[0], leftPair[1]);
        double deltaRight = distance(rightPair[0], rightPair[1]);

        if(deltaLeft < deltaRight) // placeholder
            return leftPair;
        else
            return rightPair;

        //return pts;
    }

    private double distance(Point p1, Point p2) {
        double diffX = Math.pow(p1.getX() - p2.getX(), 2);
        double diffY = Math.pow(p1.getY() - p2.getY(), 2);
        return Math.sqrt(diffX + diffY);
    }

    private Point[] threePointsMin(Point p1, Point p2, Point p3) {
        Point[] pts = new Point[2];
        double d1 = distance(p1, p2);
        double d2 = distance(p1, p3);
        double d3 = distance(p2, p3);

        if(d1 < d2 && d1 < d3) {
            pts[0] = p1;
            pts[1] = p2;
        } else if(d2 < d3) {
            pts[0] = p1;
            pts[1] = p3;
        } else {
            pts[0] = p2;
            pts[1] = p3;
        }

        return pts;
    }
}
