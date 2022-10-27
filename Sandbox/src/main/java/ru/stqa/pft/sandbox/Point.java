package ru.stqa.pft.sandbox;

import org.testng.annotations.Test;

public class Point {
    public double x;
    public double y;


    public static double distance(Point p1, Point p2) {
        double distance = Math.sqrt ((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y ) * (p2.y - p1.y));
        return distance;
    }
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static void main(String[] args) {

    }
}