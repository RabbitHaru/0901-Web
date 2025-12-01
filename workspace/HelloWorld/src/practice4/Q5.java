package practice4;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Q5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		5.정수를 10개 입력받아 배열에 저장하고 오름차순으로 정렬하여 출력하라. 
//		[목적-배열과 for 반복문 연습] [난이도 중]
//	   정수 10개 입력>>17 3 9 -6 77 234 5 23 -3 1
//	   -6 -3 1 3 5 9 17 23 77 234
		Scanner sc = new Scanner(System.in);
		int[] Arr = new int[10];
		
		System.out.print("정수 10개 입력>>");
		for(int i = 0; i<Arr.length; i++) {
			Arr[i] = sc.nextInt();
		}
		
		// 정렬을 하기위한 배열을 설정
		Arrays.sort(Arr);
		// 배열에 저장된 내용을 출력하는 반복문
//		내림차순 정렬 실행할 시 int가 아닌 Integer로 실행해야 에러가 나지 않는다.
		Integer[] intArr3 =  {17, 3, 9, -6, 77, 234, 5, 23, -3, 1};
//		 Arrays.sort(배열, Collections.reverseOrder()): 내림차순 정렬을 실행하는 메서드
		Arrays.sort(intArr3, Collections.reverseOrder());
//		Arrays.toString(배열) : 배열을 문자열로 변경하여 출력하는 메서드
		System.out.println(Arrays.toString(intArr3));
		for(int num:Arr) {
			System.out.print(num + " ");
		}
		sc.close();
	}

}
