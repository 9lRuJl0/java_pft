package ru.stqa.pft.sandbox;

import org.testng.annotations.Test;

public class PointTests {
    @Test
        public void testDistance() {

        Point p1 = new Point (3, 4);
        Point p2 = new Point(5, 6);

            assert Point.distance(p1, p2) == 2.8284271247461903;

    }

    public void testDistancePointp2 () {
        Point p2 = new Point(5, 6);

    }
}

