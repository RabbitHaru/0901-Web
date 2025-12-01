package practice7;

class Student {
	private int sno;
	private String name;
	private String dept;
	private int[] scores = new int[3];
	private double avg;
	private int rank;

	Student(int sno, String name, String dept, int s1, int s2, int s3) {
		this.sno = sno;
		this.name = name;
		this.dept = dept;
		this.scores[0] = s1;
		this.scores[1] = s2;
		this.scores[2] = s3;
		this.avg = getAvg();
	}

	int getTotal() {
		int total = 0;
		for(int score : scores) {
			total+=score;
		}
		return total;
	}

	double getAvg() {
		return getTotal() / scores.length;
	}

	void getRank(Student[] students) {
		int rank = 1;
		for(Student student : students) {
			if(student.getTotal()>this.getTotal()) {
				rank++;
			} 
			this.rank = rank;
		}
	}
	void print() {
		System.out.println(sno+" "+name+" "+dept+" "+scores[0]
				+" "+scores[1]+" "+scores[2]+" "+getTotal()
				+" "+getAvg()+" "+rank);
	}

}

public class Q1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		1. 다음과 같이 출력이 되도록 프로그램을 작성하라. 
//		클래스 명 : Student
//		필드 : 학번(sno:int), 이름(name:String), 학과(dept:String), 성적1~3(int[] socres),
//		평균(avg:double), 등수(rank: int)
//		생성자(학번, 이름, 학과, 성적1, 정적2, 성적3) 초기화하는 생성자 구현
//		총점, 평균, 석차 구하기 메소드
//		출력 메소드
//		Student[] students=new Student[3]; 사용
//		학번 이름 학과 성적1 성적2 성적3 총점 평균 석차
//		1 홍길동 영문 80 70 80 230 76.66 2
//		2 박경미 컴공 70 80 75 225 75 3
//		3 하성호 전컴 80 85 90 255 85 1
		Student[] students=new Student[3];
		
		students[0] = new Student(1, "홍길동", "영문", 80, 70, 80);
		students[1] = new Student(2, "박경미", "컴공", 70, 80, 75);
		students[2] = new Student(3, "하성호", "전컴", 80, 85, 90);
		
		for(Student s:students) {
			s.getRank(students);
		}
	
		System.out.println("학번 이름 학과 성적1 성적2 성적3 총점 평균 석차");
		for (Student s : students) {
			s.print();
		}
	}

}
