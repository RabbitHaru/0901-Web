package practice7;

class Phone {
	private String manufacturer;
	private int price;
	private int telType;

	Phone(String manufacturer, int price, int telType) {
		this.manufacturer = manufacturer;
		this.price = price;
		this.telType = telType;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getTelType() {
		return telType;
	}

	public void setTelType(int telType) {
		this.telType = telType;
	}

	public void printInfo() {
		System.out.println("제조사 : " + manufacturer);
		System.out.println("가격 : " + price);
		System.out.println("통신타입 : " + telType);
	}
}

class Smartphone extends Phone {
	private String osType;
	private int osVer;
	private int inMemory;
	private String camera;
	private String bluetooth;

	Smartphone(String manufacturer, int price, int telType, String osType, int osVer, int inMemory, String camera,
			String bluetooth) {
		super(manufacturer, price, telType);
		this.osType = osType;
		this.osVer = osVer;
		this.inMemory = inMemory;
		this.camera = camera;
		this.bluetooth = bluetooth;
	}

	public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	public int getOsVer() {
		return osVer;
	}

	public void setOsVer(int osVer) {
		this.osVer = osVer;
	}

	public int getInMemory() {
		return inMemory;
	}

	public void setInMemory(int inMemory) {
		this.inMemory = inMemory;
	}

	public String camera() {
		return camera;
	}

	public void setCamera(String camera) {
		this.camera = camera;
	}

	public String bluetooth() {
		return bluetooth;
	}

	public void setBluetooth(String bluetooth) {
		this.bluetooth = bluetooth;
	}
	
	// 지원여부 출력 메서드
	public String getBool(boolean bool) {
		return bool ? "지원" : "미지원";
	}
	
	@Override
	public void printInfo() {
		// TODO Auto-generated method stub
		super.printInfo();
		System.out.println("운영체제 타입 : " + osType);
		System.out.println("운영체제 버전 : " + osVer);
		System.out.println("내부 메모리 크기 : " + inMemory);
		System.out.println("카메라 장착 여부 : " + camera);
		System.out.println("블루투스 지원 여부 : " + bluetooth);
	}
}

public class Q5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		5. 일반적인 휴대폰을 나타내는 Phone 클래스를 작성한다. Phone에는 제조사, 가격, 통신타
//		입(2g 또는 3g) 등의 정보가 저장되어 있다. Phone에서 상속받아서 SmartPhone 클래스
//		를 작성하여 보자. SmartPhone 클래스에는 운영체제 타입, 운영체제 버전, 내부 메모리
//		크기, 카메라 장착 여부, 블루투스 지원 여부 등의 필드가 추가된다. 생성자, 접근자, 설
//		정자를 포함하여서 각각의 클래스를 작성한다. 이들 클래스들의 객체를 만들고 각 객체
//		의 모든 정보를 출력하는 테스트 클래스를 작성하라. 
		Smartphone s1 = new Smartphone("삼성", 1500000, 3, "윈도우", 12, 120, "있음", "있음");
		Smartphone s2 = new Smartphone("애플", 2500000, 3, "Mac", 15, 1230, "있음", "있음");

		s1.printInfo();
		System.out.println();
		s2.printInfo();
	}

}
