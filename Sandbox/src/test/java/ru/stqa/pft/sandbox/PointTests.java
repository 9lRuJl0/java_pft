package ru.stqa.pft.sandbox;

import org.testng.annotations.Test;

public class PointTests {
    @Test
        public void testDistance(Point p1, Point p2) {

            p1 = new Point(3, 4);
            p2 = new Point(5, 6);

            assert Point.distance(p1, p2) == 2.8284271247461903;

    }
    @Test
        public  void test2distanse(Point p1, Point p2) {

        p1 = new Point(3, 4);
        p2 = new Point(5, 6);

        assert p1.distance(p2) == 2.8284271247461903;

    }

}

