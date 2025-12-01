package TEST2;

import java.util.Scanner;

class NumberGame {
	private int answer;
	private int attempts;

	NumberGame() {
		this.answer = (int) (Math.random() * 100) + 1;
		this.attempts = 0;
	}

	int getAnswer() {
		return answer;
	}

	int getAttempts() {
		return attempts;
	}

	void incrementAttempts() {
		attempts++;
	}

	String checkGuess(int guess) {
		incrementAttempts();
		if (guess < answer)
			return "더 큰 수 입니다.";
		else if (guess > answer)
			return "더 작은 수 입니다.";
		else
			return attempts + "회 만에 맞췄습니다.";
	}

	String getHint() {
		return (answer % 2 == 0) ? "짝수" : "홀수";
	}

}

public class Q1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		NumberGame game = new NumberGame();

		System.out.println("=== 숫자맞추기 게임을 시작합니다. ===");
		System.out.println("컴퓨터가 숫자를 생각했습니다.");
		System.out.println("힌트는 " + game.getHint() + "입니다.");

		while (true) {
			System.out.print("1~100 사이의 값을 입력 >>");
			int guess = sc.nextInt();

			String result = game.checkGuess(guess);
			System.out.println(result);

			if (result.contains(game.getAttempts() + "회 만에 맞췄습니다.")) {

				break;

			}
		}
		System.out.println("=== 게임을 종료합니다. ===");
		sc.close();
	}

}
