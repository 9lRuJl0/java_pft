package ru.stqa.pft.sandbox;

public class MyFirstProgram {
	
	public static void main(String[] agrs) {
		hello("world");
		hello ("Ярослав");
		hello ("user");

		Square s = new Square(5);
		System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

		Rectangle r = new Rectangle(4, 6);
		System.out.println("Площадь пряумоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
		}

	public static void hello(String somebody)  {
		System.out.println("Hello, " + somebody + "!");
}

    System.out.println("Расстояние между двумя точками на координатной плостокти" + " = " double distance);

    public static double distance(Point p1, Point p2) {
     double distance = Math.sqrt ((p2.x2 - p1.x1) * (p2.x2 - p1.x1) + (p2.y2 - p1.y1 ) * (p2.y2 * p1.y1));
	 return distance;
	}
	
}