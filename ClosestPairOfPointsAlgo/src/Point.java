import java.util.Scanner;

public class Point {
    private double x;
    private double y;

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

    public void print() {
        System.out.println("[" + x + "," + y + "]");
    }

}
