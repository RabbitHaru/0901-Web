package practice7;

class Food{
	private int cal;
	private int cost;
	private int kg;
	Food(int cal, int cost, int kg){
		this.cal=cal;
		this.cost=cost;
		this.kg=kg;
	}
	public int getCal() {
		return cal;
	}
	public int getCost() {
		return cost;
	}
	public int getKg() {
		return kg;
	}
	
	public void setCal(int cal) {
		this.cal=cal;
	}
	public void setCost(int cost) {
		this.cost=cost;
	}
	public void setKg(int kg) {
		this.kg=kg;
	}
	
	public void printInfo() {
		System.out.println("칼로리 : " + cal);
		System.out.println("가격 : " + cost);
		System.out.println("무게 : " + kg);
	}
}

class Melon extends Food{
	String farmInfo;
	Melon(int cal, int cost, int kg,String farmInfo){
		super(cal, cost, kg);
		this.farmInfo=farmInfo;
	}
	
	public String getFarmInfo() {
		return farmInfo;
	}
	
	public void setFarmInfo(String farmInfo) {
		this.farmInfo=farmInfo;
	}
	
	@Override
	public void printInfo() {
		// TODO Auto-generated method stub
		super.printInfo();
		System.out.println("경작 농원 정보 : "+farmInfo);
	}
	
}
public class Q4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		4. 일반적인 음식을 나타내는 Food 클래스를 상속받아서 멜론을 나타내는 Melon 클래스를
//		작성하여 보자. Food 클래스는 칼로리, 가격, 중량 등의 정보를 가진다. Melon 클래스는
//		추가로 경작 농원 정보를 가진다. 생성자, 접근자, 설정자를 포함하여서 각각의 클래스를
//		작성한다. 이들 클래스들의 객체를 만들고 각 객체의 모든 정보를 출력하는 테스트 클래
//		스를 작성하라. 
		
		Melon m1 = new Melon(152,14999,14,"안녕하세요");
		Melon m2 = new Melon(1552,149992,144,"안녕하세요2");
		
		m1.printInfo();
		System.out.println();
		m2.printInfo();
	}

}
