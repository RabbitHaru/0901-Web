package practice7;

class Student2 {
	private String name;
	private int studentId;
	private String department;
	private int grade;
	private int creditsCompleted;

	Student2(String name, int studentId, String department, int grade, int creditsCompleted) {
		this.name = name;
		this.studentId = studentId;
		this.department = department;
		this.grade = grade;
		this.creditsCompleted = creditsCompleted;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getCreditsCompleted() {
		return creditsCompleted;
	}

	public void setCreditsCompleted(int creditsCompleted) {
		this.creditsCompleted = creditsCompleted;
	}

	public void printInfo() {
		System.out.println("이름 : " + name + "/ 학번 : " + studentId + "/ 학과 : " + department + "/ 학년 : " + grade
				+ "/ 이수학점 : " + creditsCompleted);

	}
}

class undergraduateStudent extends Student2 {
	String club;

	undergraduateStudent(String name, int studentId, String department, int grade, int creditsCompleted,
			String club) {
		super(name, studentId, department, grade, creditsCompleted);
		this.club = club;
	}

	public String getClub() {
		return club;
	}

	public void setClub(String club) {
		this.club = club;
	}

	@Override
	public void printInfo() {
		// TODO Auto-generated method stub
		super.printInfo();
		System.out.print("/ 소속 동아리 : " + club);
	}
}

class assistant extends Student2 {
	String ta_type;
	String scholarship_yn;
	assistant(String name, int studentId, String department, int grade, int creditsCompleted,String ta_type,String scholarship_yn){
		super(name, studentId, department, grade, creditsCompleted);
		this.ta_type=ta_type;
		this.scholarship_yn=scholarship_yn;
	}
	public String getTa_type() {
		return ta_type;
	}
	public String getScholarship_yn() {
		return scholarship_yn;
	}
	
	public void setScholarship_yn(String scholarship_yn) {
		this.scholarship_yn = scholarship_yn;
	}
	public void setTa_type(String ta_type) {
		this.ta_type = ta_type;
	}
	
	@Override
	public void printInfo() {
		super.printInfo();
		System.out.print("조교 유형 : "+ta_type+"/ 장학금 여부 : "+scholarship_yn);
	}
}


public class Q6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		6. 다음 그림에 해당하는 클래스를 작성하여 보자. 모든 학생은 이름, 학번, 소속 학과, 학
//		년, 이수 학점 수를 가진다. 추가적으로 학부생은 소속 동아리명을 가지고 있고 대학원생
//		은 조교 유형과 장학금 비율을 가진다. 조교 유형에는 교육 조교와 연구 조교가 있으며
//		장학금 비율은 0과 1사이의 값이다. 각 클래스는 적절한 생성자 메소드, 접근자 메소드, 변경자 메소드를 가진다. 이러한 클래스들의 객체를 만들고 각 객체의 모든 정보를 출력
//		하는 테스트 클래스를 작성하라
		undergraduateStudent u1 = new undergraduateStudent("갑", 1000, "컴공", 3, 84, "날자날어");
		undergraduateStudent u2 = new undergraduateStudent("갑", 1000, "컴공", 3, 87, "돌고 돌아");
		
		assistant a1 = new assistant("을", 100, "전자공학", 2, 51, "교육 조교", "안해");
		assistant a2 = new assistant("병", 102, "세포생물", 3, 61, "연구 조교", "함");
		
		u1.printInfo();
		System.out.println();
		u2.printInfo();
		System.out.println();
		a1.printInfo();
		System.out.println();
		a2.printInfo();
		
	}
	
}
