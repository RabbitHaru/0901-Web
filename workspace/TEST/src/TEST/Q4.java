package TEST;

import java.util.HashMap;
import java.util.Scanner;

public class Q4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		HashMap<String, Integer> drinks = new HashMap<>();

		drinks.put("밀키스", 1000);
		drinks.put("코카콜라", 800);
		drinks.put("펩시", 700);
		drinks.put("칠성사이다", 1200);

		System.out.println("밀키스, 코카콜라, 펩시, 칠성사이다 있습니다.");

		while (true) {

			System.out.print("선택>>");
			String input = sc.nextLine();
			
			if (input.equals("그만")) {
				System.out.println("종료합니다...");
				break;
			}
			if (drinks.containsKey(input)) {
				System.out.println(input + "는 " + drinks.get(input) + "원 입니다.");
			} else {
				System.out.println("없는 음료수입니다. 정확히 다시 입력바랍니다.");
			}
		}
		sc.close();
	}

}
