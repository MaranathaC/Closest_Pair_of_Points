import java.util.Scanner;

/**
 * Class to represent a Point
 * A point is represented by its X and Y coordinates
 * Index represents its position in an array of points
 * @author Natha Chiu
 */

public class Point {
    private final double x;
    private final double y;
    private int index;

    /**
     * constructor
     * pre: sc has a txt file, and file is formatted properly
     * post: x and y coordinates are initialized
     */
    public Point(Scanner sc) {
        x = sc.nextDouble();
        y = sc.nextDouble();
    }

    /**
     *  getter for x
     *  pre: none
     *  post: returns x coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * getter for y
     * pre: none
     * post: returns y coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * getter for index
     * pre: index is set
     * post: return index
     */
    public double getIndex() {
        return index;
    }

    /**
     * setter for index
     * pre: none
     * post: index is set
     */
    public void setIndex(int index) {
        this.index = index;
    }
}
