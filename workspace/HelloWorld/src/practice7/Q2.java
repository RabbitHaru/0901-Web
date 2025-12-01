package practice7;

class Person{
	private String name;
	private String adress;
	private String phoneNum;
	
	Person(String name,String adress,String phoneNum){
		this.name=name;
		this.adress=adress;
		this.phoneNum=phoneNum;	
	}
	public String getName(){
		return name;
	}
	public String getAdress(){
		return adress;
	}
	public String getPhoneNum(){
		return phoneNum;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	public void getAdress(String adress) {
		this.adress=adress;
	}
	public void getPhoneNum(String phoneNum) {
		this.phoneNum=phoneNum;
	}
	
	public void printInfo() {
		System.out.println("이름:"+name);
		System.out.println("주소:"+adress);
		System.out.println("전화번호:"+phoneNum);
	}
	
}
class Customer extends Person{
	private int customerNo;
	private int mileage;
	Customer(String name, String adress, String phoneNum,
			int customerNo,int mileage) {
		super(name, adress, phoneNum);
		this.customerNo=customerNo;
		this.mileage=mileage;
	}
	public int getCustomerNo() {
		return customerNo;
	}
	public int getMileage() {
		return mileage;
	}
	
	public void setCustomerNo(int customerNo) {
		this.customerNo=customerNo;
	}
	public void setMileage(int mileage) {
		this.mileage=mileage;
	}
	@Override
	public void printInfo() {
		super.printInfo();
		System.out.println("고객번호:"+customerNo);
		System.out.println("마일리지:"+mileage);
	}
}


public class Q2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		2. Person 클래스를 설계하라. Person 클래스는 이름, 주소, 전화 번호를 필드로 가진다. 
//		하나 이상의 생성자를 정의하고 각 필드에 대하여 접근자와 설정자 메소드를 작성하라. 
//		이어서 Person을 상속받아서 Customer를 작성하여 보자. Customer는 고객 번호와 
//		마일리지를 필드로 가지고 있다. 한 개 이상의 생성자를 작성하고 적절한 접근자 메소드와 
//		설정자 메소드를 작성한다. 이들 클래스들의 객체를 만들고 각 객체의 모든 정보를 
//		출력하는 테스트 클래스를 작성하라.
		Person p1 = new Person("홍길동", "서울시 은평구 ", "010-0000-0000");
		
		Customer c1 = new Customer("김제노", "부산시 부산진구", "010-0000-0000", 444, 6666666);
		
		p1.printInfo();
		System.out.println();
		c1.printInfo();
	}

}
