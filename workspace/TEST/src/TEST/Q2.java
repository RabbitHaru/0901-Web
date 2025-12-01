package TEST;

import java.util.Scanner;

//[문제2] Scanner를 이용하여 한 라인을 읽고,
//,(콤마)로 분리된 어절이 몇 개인지 출력을 반복하는
//프로그램을 작성하시오. “exit”이 입력되면 종료한다.

public class Q2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		while (true) {
			String input = sc.nextLine();

			if (input.equals("exit")) {
				System.out.println("종료합니다...");
				break;
			}
			String[] words = input.split(",");
			System.out.println("어절 개수는 " + words.length);
		}
	}

}
