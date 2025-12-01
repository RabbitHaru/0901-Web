package TEST;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Q5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		HashMap<String, Integer> nations = new HashMap<String, Integer>();

		System.out.println("나라 이름과 인구를 5개 입력하세요. (예: Korea 5000)");

		for (int i = 1; i <= 5; i++) {
			System.out.print("나라 이름, 인구 >>");
			String input = sc.next();
			int population = sc.nextInt();
			nations.put(input, population);
		}

		String smallestNations = null;
		int smallestPopulation = Integer.MAX_VALUE;

		for (Map.Entry<String, Integer> entry : nations.entrySet()) {
			if (entry.getValue() < smallestPopulation) {
				smallestPopulation = entry.getValue();
				smallestNations = entry.getKey();
			}
		}
		System.out.println("제일 인구가 적은 나라는 (" + smallestNations + ", " + smallestPopulation + ")");

	}

}
