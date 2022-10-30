package ru.stqa.pft.sandbox;

public class Point {
    public double x;
    public double y;


    public static double distance(Point p1, Point p2) {
        double distance = Math.sqrt ((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y ) * (p1.y - p2.y));
        return distance;
    }
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance (Point p2) {

        return Math.sqrt ((this.x - p2.x) * (this.x - p2.x) + (this.y - p2.y ) * (this.y - p2.y));
    }
   //public static void main(String[] args) {
        //Point p1 = new Point(4, 5);
        //Point p2 = new Point(6, 7);

   // }
}