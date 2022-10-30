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


		Point p1 = new Point(3, 4);
		Point p2 = new Point(5, 6);


		System.out.println("Растояние между точками p1" + " и " + " p2 " + " = " + Point.distance(p1, p2));
		System.out.println("Растояние между точками p1" + " и " + " p2 " + " = " + p1.distance(p2));
		}


	public static void hello(String somebody)  {
		System.out.println("Hello, " + somebody + "!");
}

	
}