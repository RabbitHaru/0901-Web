package TEST;

import java.util.HashMap;
import java.util.Scanner;

public class Q3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		HashMap<String, Integer> map = new HashMap<>();

		System.out.println("<< 통장 관리 프로그램입니다. >>");

		while (true) {
			System.out.print("이름과 금액 입력>> ");
			String name = sc.next();

			if (name.equals("exit")) {
				System.out.println("프로그램을 종료합니다...");
				break;
			}

			int money = sc.nextInt();

//			중복 데이터 확인 후 기존 데이터를 수정
			if (map.containsKey(name)) {
				map.put(name, map.get(name) + money);
			} else {
				map.put(name, money);
			}
//			맵을 반복하여 데이터 출력하는 방식
			for (String key : map.keySet()) {
				System.out.print("(" + key + ">" + map.get(key) + "원)");
			}
			System.out.println();
		}
		sc.close();
	}

}
