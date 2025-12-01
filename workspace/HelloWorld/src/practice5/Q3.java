package practice5;

import java.util.Scanner;

// Rect 클래스: 사각형의 너비(width)와 높이(height)를 저장하고,
//  면적을 계산하는 클래스
class Rect { 
	// 사각형의 가로(width), 세로(height)를 저장할 변수
private int width, height; 

// 생성자(Constructor): 객체가 만들어질 때(width, height)를
//  전달받아 초기화하는 역할
public Rect(int width, int height) { 
this.width = width; // 전달받은 width 값을 필드(멤버 변수)에 저장
this.height = height;  // 전달받은 height 값을 필드에 저장
} 
//getArea 메서드: 사각형의 면적(가로 * 세로)을 계산해서 반환
public int getArea() { return width*height; } 
} 

public class Q3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		3. 사각형을 표현하는 다음 Rect 클래스를 활용하여, Rect 객체 배열을 생성하고, 사용자로부
//		터 4개의 사각형을 입력받아 배열에 저장한 뒤, 배열을 검색하여 사각형 면적의 합을 출
//		력하는 main() 메소드를 가진 RectArray 클래스를 작성하라. [목적 – 객체 배열 활용]
//		[난이도 중]
		Scanner sc = new Scanner(System.in);
		
		
		Rect[] rectArr = new Rect[4];
		for(int i=0; i<rectArr.length; i++) {
			System.out.print(i+1+"너비와 높이 >>");
			int width = sc.nextInt();
			int height = sc.nextInt();
			rectArr[i] = new Rect(width,height);
			
		}
		System.out.println("저장하였습니다...");
		int sum = 0;
		for(Rect rect : rectArr ) {
			// 각 Rect의 넓이를 더하는 코드
			sum += rect.getArea();
		}
		System.out.println("사각형 전체의 합은 " + sum);
//		-----------------------------------------------------------------------------
//		1 너비와 높이 >>3 5
//		2 너비와 높이 >>3 9
//		3 너비와 높이 >>2 7
//		4 너비와 높이 >>9 5
//		저장하였습니다…
//		사각형의 전체 합은 101
//		-----------------------------------------------------------------------------
	}

}
