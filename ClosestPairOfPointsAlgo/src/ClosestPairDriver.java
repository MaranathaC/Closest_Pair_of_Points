import java.io.*;
import java.util.Random;
import static org.junit.Assert.*;
/**
 * Driver Class for ClosestPair
 * @author Natha Chiu
 */
public class ClosestPairDriver {
    /**
     * main
     * pre: ClosestPair class is implemented
     * post: find closest pair of points
     */
    public static void main(String[] args) {
        for(int i = 0; i < 100; i++) {
            genData();
            ClosestPair closestPair = new ClosestPair();
            Point[] points = closestPair.findPair();
            Point[] points1 = closestPair.bruteForce();
            System.out.println();
            assertEquals(distance(points), distance(points1), 0.0001);
        }
    }

    /**
     * genData
     * pre: file in correct directory
     * post: write size and random points to file
     */
    public static void genData() {
        Random rand = new Random();
        int size = rand.nextInt(10000);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("ClosestPairOfPointsAlgo/src/program2data.txt"));
            writer.write(size + "\n");
            for (int i = 0; i < size; i++) {
                double x = rand.nextDouble();
                x *= 10000;
                double y = rand.nextDouble();
                y *= 10000;
                String xs = String.format("%.4f", x);
                String ys = String.format("%.4f", y);
                writer.write(xs + " " + ys + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static double distance(Point[] pts) {
        // sqrt((x1-x2)^2 + (y1-y2)^2)
        double diffX = Math.pow(pts[0].getX() - pts[1].getX(), 2);
        double diffY = Math.pow(pts[0].getY() - pts[1].getY(), 2);
        return Math.sqrt(diffX + diffY);
    }
}
