import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class ClosestPair {
    private Point[] sortedX;

    public ClosestPair() {
        Scanner sc;
        try {
            sc = new Scanner(new File("ClosestPairOfPointsAlgo/src/program2data.txt"));

            int size = sc.nextInt();
            sortedX = new Point[size];

            for(int i = 0; i < size; i++) {
                sortedX[i] = new Point(sc);
            }

            Arrays.sort(sortedX, new SortPointsByX());

            for(int i = 0; i < size; i++) {
                sortedX[i].setIndex(i);
            }

            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Point[] findPair() {
        Point[] sortedY = new Point[sortedX.length];
        System.arraycopy(sortedX, 0, sortedY, 0, sortedX.length);
        Arrays.sort(sortedY, new SortPointsByY());
        return findPair(sortedX, sortedY, 0, sortedX.length);
    }

    private Point[] findPair(Point[] sortedX, Point[] sortedY, int left, int right) {
        int numOfPoints = right - left;

        // is the closest pair because there is only 2 points
        if(numOfPoints == 2) {
            Point[] pts = new Point[2];
            pts[0] = sortedX[left];
            pts[1] = sortedX[left + 1];
            printPointsAndDistance(pts, left, right - 1);
            return pts;
        }

        // find closest pair within the 3 points
        if(sortedX.length == 3) {
            Point[] pts = threePointsMin(sortedX[left], sortedX[left + 1], sortedX[left + 2]);
            printPointsAndDistance(pts, left, right - 1);
            return pts;
        }

        int mid = left + (right - left) / 2; // middle index

        Point[] sortedYLeft = new Point[mid - left]; // left half of Points, sorted by y
        Point[] sortedYRight = new Point[right - mid]; // right half of Points, sorted by y

        int leftPtr = 0, rightPtr = 0, arrPtr = 0; // indices that keep track of positions in arrays

        while(arrPtr < sortedY.length) {
            if(sortedY[arrPtr].getIndex() >= mid) { // point belongs to right half
                sortedYRight[rightPtr++] = sortedY[arrPtr++];
            } else { // point belongs to left half
                sortedYLeft[leftPtr++] = sortedY[arrPtr++];
            }
        }

        Point[] leftPair = findPair(sortedX, sortedYLeft, left, mid); // find closest pair in left
        Point[] rightPair = findPair(sortedX, sortedYRight, mid, right); // find closest pair in right

        // find minimum distance
        double deltaLeft = distance(leftPair[0], leftPair[1]);
        double deltaRight = distance(rightPair[0], rightPair[1]);
        double deltaMin = Math.min(deltaLeft, deltaRight);

        // find the boundaries of the strip
        double leftStripBound = sortedX[mid].getX() - deltaMin;
        double rightStripBound = sortedX[mid].getX() + deltaMin;

        int leftStripIndex = left, rightStripIndex = right - 1; // indices of strip

        // find point with smallest x value within the strip
        while(leftStripIndex < right && sortedX[leftStripIndex].getX() < leftStripBound) {
            leftStripIndex++;
        }

        if(leftStripIndex >= right) { // no points within the strip
            return deltaLeft < deltaRight ? leftPair : rightPair;
        }

        // find point with largest x value within the strip
        while(sortedX[rightStripIndex].getX() > rightStripBound) {
            rightStripIndex--;
        }

        Point[] strip = new Point[rightStripIndex - leftStripIndex + 1];
        int j = 0;
        for (Point point : sortedY) {
            // find points within the left and right indices, and would be sorted by y-value
            if (point.getIndex() >= leftStripIndex && point.getIndex() <= rightStripIndex) {
                strip[j++] = point; // is a point within the strip
            }
        }

        Point[] midPair = stripClosest(strip); // find the closest pair within the strip

        Point[] closest = minOfThreePairs(leftPair, midPair, rightPair); // return the closest pair
        printPointsAndDistance(closest, left, right - 1);
        return closest;
    }

    private double distance(Point p1, Point p2) {
        // sqrt((x1-x2)^2 + (y1-y2)^2)
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

    private Point[] stripClosest(Point[] strip) {
        Point[] closestInStrip = new Point[2];
        double currShortestDistance = Double.MAX_VALUE;
        for(int i = 0; i < strip.length - 1; i++) {
            for(int j = i + 1; j < strip.length && j < i + 8; j++) {
                double distance = distance(strip[i], strip[j]);
                if(distance < currShortestDistance) {
                    closestInStrip[0] = strip[i];
                    closestInStrip[1] = strip[j];
                    currShortestDistance = distance;
                }
            }
        }
        return closestInStrip;
    }

    private Point[] minOfThreePairs(Point[] leftPair, Point[] midPair, Point[] rightPair) {
        double lDist = distance(leftPair[0], leftPair[1]);
        double mDist = distance(midPair[0], midPair[1]);
        double rDist = distance(rightPair[0], rightPair[1]);

        if(lDist < mDist && lDist < rDist) {
            return leftPair;
        } else if(mDist < rDist) {
            return midPair;
        } else {
            return rightPair;
        }
    }

    private void printPointsAndDistance(Point[] pts, int left, int right) {
        System.out.print("D[" + left + "," + right + "]: ");
        System.out.printf("%.4f\n",distance(pts[0], pts[1]));
    }
}
