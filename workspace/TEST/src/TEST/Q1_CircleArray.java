package TEST;

import java.util.Scanner;

//[문제1] 원을 표현하는 다음 Circle 클래스를 활용하여,
//Circle 객체 배열을 생성하고, 사용자로부터 4개의 반지름을
//입력받아 배열에 저장한 뒤, 배열을 검색하여 원 면적의 합을
//출력하는 main() 메소드를 가진 CircleArray 클래스를 작성하시오.

class Circle {
	private int radius;

	public Circle(int radius) {
		this.radius = radius;
	}

	public double getArea() {
		return radius * radius * 3.14;
	}
}

public class Q1_CircleArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		Circle[] c = new Circle[4];

		for (int i = 0; i < c.length; i++) {
			System.out.print((i + 1) + " 반지름 >> ");
			int radius = sc.nextInt();
			c[i] = new Circle(radius);
		}

		double sum = 0;
		for (int i = 0; i < c.length; i++) {
			sum += c[i].getArea();
		}

		System.out.println("저장하였습니다...");
		System.out.println("원의 면적 전체 합은 " + sum);
	}

}
