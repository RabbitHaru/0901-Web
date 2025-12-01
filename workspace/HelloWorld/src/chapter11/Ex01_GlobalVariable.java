package chapter11;
class Cat{
//	static 변수: main이 실행되기 전부터 생성되는 변수
//	Cat클래스를 생성자로 이용하여 생성하지 않아도 사용할 수 있음.
	static int a = 5;
//	일반 멤버 변수 : Cat클래스를 생성자를 이용하여
//	생성하지 않으면 사용할 수 없는 변수
	int num = 3;
//	일반 메서드 : static변수, 멤버변수 둘다 자유롭게 사용가능
	void printValue(int num) {
		this.num = num;
		System.out.println("num:"+ this.num);
		System.out.println("a:" + a);
	}
}

public class Ex01_GlobalVariable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num1 = 5;
		int num2 = 2;
		System.out.println(num1 + "," + num2);
		
		Cat cat1 = new Cat();
		cat1.num = 1;
		cat1.a = 10;
		cat1.printValue(cat1.num);
		System.out.println(cat1.a);
		
		Cat cat2 = new Cat();
		cat2.num = 2;
		cat2.a = 11;
		cat2.printValue(cat2.num);
		System.out.println(cat2.a);
		System.out.println(cat1.a);
		System.out.println(Cat.a);
	}

}
