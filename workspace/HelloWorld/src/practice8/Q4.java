package practice8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Student {
	private String name;
	private String dept;
	private int studno;
	private double grade;

	Student(String name, String dept, int studno, double grade) {
		this.name = name;
		this.dept = dept;
		this.studno = studno;
		this.grade = grade;
	}

	String getName() {
		return name;
	}

	void showinfo() {

		System.out.println("-------------------");
		System.out.println("이름 :" + name);
		System.out.println("학과 :" + dept);
		System.out.println("학번 :" + studno);
		System.out.println("학점 :" + grade);
	}

	void printInfo() {
		System.out.println(name + ", " + dept + ", " + studno + ", " + grade);
	}
}

public class Q4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		4. 학생정보를 나타내는 Student클래스에 이름, 학과, 학번, 학점을 저장하는 필드를 작성하라.
//		(1) 학생 객체를 생성하고 5명을 학생정보를 ArrayList<Student>컬렉션에 저장한 후에,
//		ArrayList<Student>의 모든학생(5명) 정보를 출력하고 학생의 이름을 입력받아 해당 학생의 학생정
//		보를 출력하는 프로그램을 작성하라.
		Scanner sc = new Scanner(System.in);
		ArrayList<Student> list = new ArrayList<>();

		System.out.println("학생이름, 학과, 학번, 학점을 입력하세요.");
		
		for(int i=0; i<5; i++) {
			System.out.println(">> ");
			String name = sc.next();
			String dept = sc.next();
			Integer studno = sc.nextInt();
			Double grade = sc.nextDouble();
			list.add(new Student(name,dept,studno,grade));
		}
		for(Student s : list) {
			s.showinfo();

		}
		System.out.println("------------------");
		
		while (true) {
			System.out.print("학생이름 >> ");
			String search= sc.next();
			if(search.equals("그만")) {
				break;
				
			}
			for(Student s : list) {
				if(s.getName().equals(search)) {
					s.printInfo();
				}
		}
		
	}
		
		
//		(2) ArrayList<Student> 대신, HashMap<String, Student> 해시맵을 이용하여 다시 작성하라. 해시
//		맵의 키(key)는 학생이름으로 한다.
		HashMap<String,Student> studentmap = new HashMap<>();
		System.out.println("학생이름, 학과, 학번, 학점을 입력하세요.");
		
		for(int i=0; i<5; i++) {
			System.out.println(">> ");
			String name = sc.next();
			String dept = sc.next();
			Integer studno = sc.nextInt();
			Double grade = sc.nextDouble();
			
			Student s =  new Student(name, dept, studno,grade);
			studentmap.put(name, s);
		}
		
		System.out.println("\n[전체 학생 목록]");
		for (String name : studentmap.keySet()) {
			Student s = studentmap.get(name);
			s.showinfo();
		}
		
		while (true) {
			System.out.print("학생이름 >> ");
			String search= sc.next();
			if(search.equals("그만")) {
				break;
				
			}
			for(Student s : list) {
				if(s.getName().equals(search)) {
					s.printInfo();
				}
			}
		
		}
	}
}
