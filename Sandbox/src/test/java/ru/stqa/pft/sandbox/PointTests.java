package ru.stqa.pft.sandbox;

import org.testng.annotations.Test;

public class PointTests {
    private Point p1;

    @Test
        public void testDistance() {

        Point p1 = new Point(3, 4);
        Point p2 = new Point(5, 6);

        assert Point.distance(p1, p2) == 2.8284271247461903;
    }

    @Test
    public void testDistancePoint2 () {
        Point p1 = new Point(3, 4);
        Point p2 = new Point(5, 6 );

        assert p1.distance(p2) == 2.8284271247461903;
    }
}




