import java.util.Scanner;

public class Point {
    private final double x;
    private final double y;
    private int index;

    public Point(Scanner sc) {
        x = sc.nextDouble();
        y = sc.nextDouble();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getIndex() { return index; }

    public void setIndex(int index) {
        this.index = index;
    }

    public void print() {
        System.out.println("[" + x + "," + y + "]");
    }

}
