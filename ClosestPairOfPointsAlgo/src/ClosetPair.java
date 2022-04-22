import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class ClosetPair {
    private Point[] sortedX;
    private Point[] sortedY;


    public ClosetPair() {
        Scanner sc;
        try {
            sc = new Scanner(new File("ClosestPairOfPointsAlgo/src/program2data.txt"));
            int size = sc.nextInt();
            sortedX = new Point[size];
            sortedY = new Point[size];

            for(int i = 0; i < 8; i++) {
                sortedX[i] = new Point(sc);
                sortedY[i] = sortedX[i];
            }

            Arrays.sort(sortedX, new SortPointsByX());
            Arrays.sort(sortedY, new SortPointsByY());

            for(Point pt : sortedX) {
                pt.print();
            }

            System.out.println();

            for (Point pt: sortedY) {
                pt.print();
            }

            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
