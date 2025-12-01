package chapter12;

class Animal {
	// 멤버 변수 : name, age,
	// 메서드 : eat(), move()
	String name;
	int age;

	Animal(String name, int age) {
		this.name = name;
		this.age = age;
	}

	void eat() {
		System.out.println(name + "가 음식을 먹습니다.");
	}

	void move() {
		System.out.println(name + "가 움직입니다.");
	}

	void printUnit() {
		System.out.println("이름 : " + name + ", 나이 : " + age);
	}
}

class Cat extends Animal {
	// 메서드 : meow() : 고양이가 야옹하고 웁니다.
	Cat(String name, int age) {
		super(name, age);
	}

	void meow() {
		System.out.println("고양이가 야옹하고 웁니다.");
	}

//	eat()메서드를 오버라이딩하여 고양이는 천천히 먹습니다 출력하도록 변경
//	move()메서드를 오버라이딩하여 고양이는 조용히 움직입니다 출력하도록 변경
	@Override
	void eat() {
		System.out.println(name + "는 음식을 천천히 먹습니다.");
	}

	@Override
	void move() {
		System.out.println(name + "는 조용히 움직입니다");
	}

	void printCat() {
		eat();
		move();
		meow();
	}
}

class Dog extends Animal {
	// 메서드 : bark() : 강아지가 멍멍하고 짖습니다.
	Dog(String name, int age) {
		super(name, age);
	}

	void bark() {
		System.out.println("강아지가 멍멍하고 짖습니다.");
	}

	// eat()를 실행하면 먹습니다. 주인의 허락이 떨어져야 먹습니다.
	@Override
	void eat() {
		super.eat();
		System.out.println("주인의 허락이 떨어져야 먹습니다.");
		System.out.println();
	}

	// move()를 실행하면 움직입니다. 산책할때는 더 빠르게 움직입니다.
	@Override
	void move() {
		super.move();
		System.out.println("산책할때는 더 빠르게 움직입니다.");
		System.out.println();
	}

	void printDog() {
		eat();
		move();
		bark();
	}
}

public class Ex01_1_UseAnimal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cat unit1 = new Cat("고양이", 2);
		unit1.printCat();
		unit1.printUnit();
		System.out.println();

		Dog unit2 = new Dog("강아지", 4);
		unit2.printDog();
		unit2.printUnit();
		System.out.println();
	}

}
