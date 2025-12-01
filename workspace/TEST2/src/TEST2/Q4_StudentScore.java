package TEST2;

public class Q4_StudentScore {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] scores = { { 80, 60, 70 }, { 90, 95, 80 }, { 75, 80, 100 }, { 80, 70, 95 }, { 100, 65, 80 } };

		int n = scores.length;
		int m = scores[0].length;

		System.out.println("학생번호   국어   영어   수학    총점    평균");
		System.out.println("=============================================");

		for (int i = 0; i < n; i++) {
			int total = 0;
			for (int j = 0; j < m; j++) {
				total += scores[i][j];
			}
			double avg = Math.round((total / (double) m) * 10) / 10.0;

			System.out.println((i + 1) + "번 학생: " + scores[i][0] + "     " + scores[i][1] + "     " + scores[i][2]
					+ "     " + total + "     " + avg);
		}
	}

}
