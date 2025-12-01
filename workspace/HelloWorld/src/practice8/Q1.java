package practice8;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class Q1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		1. 학점(‘A’, ‘B’, ‘C’, ‘D’, ‘F’)을 컬렉션에 저장하라. 그러고 나서 컬렉션을 검색하여 학점을 점수
//		(A=4.0, B=3.0, C=2.0, D=1.0, F=0.0)로 변환하여 출력하는 프로그램을 작성하라.
//		1) Vector 컬렉션을 이용
		Vector<String> gradesList = new Vector<>();
		gradesList.add("A");
		gradesList.add("B");
		gradesList.add("C");
		gradesList.add("D");
		gradesList.add("F");

		System.out.println(gradesList);
		
		for(String grade : gradesList) {
			double score = 0;
			switch(grade) {
			case "A":
				score = 4.0;
				break;
			case "B":
				score = 3.0;
				break;
			case "C":
				score = 2.0;
				break;
			case "D":
				score = 1.0;
				break;
			case "F":
				score = 0.0;
				break;
			default :
					System.out.println("잘못된 학점을 입력하셨습니다.");
					continue;
			}
			System.out.println("입력한 학점 : "+grade+", 점수 : "+score);
		}
		System.out.println();
//		2) HashMap 컬렉션을 이용
		HashMap<String, Double> gradesList2 = new HashMap<>();
		gradesList2.put("A",4.0);
		gradesList2.put("B",3.0);
		gradesList2.put("C",2.0);
		gradesList2.put("D",1.0);
		gradesList2.put("F",0.0);
		
		System.out.println("학점-점수 매칭:"+gradesList2);
		
		for(String grade : gradesList2.keySet()) {
			double score = gradesList2.get(grade);
			System.out.println("입력한 학점 : "+grade+", 점수 : "+score);
		}
	}
}
		
	