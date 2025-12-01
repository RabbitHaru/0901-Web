package practice7;

class Book{
	private String title;
	private int page;
	private String author;
	
	Book(String title, int page, String author){
		this.title=title;
		this.page=page;
		this.author=author;
	}
	
	public String getTitle() {
		return title;
	}
	public int getPage() {
		return page;
	}
	public String getAuthor() {
		return author;
	}
	
	public void setTitle(String title) {
		this.title=title;
	}
	public void setPage(int page) {
		this.page=page;
	}
	public void setAuthor(String author) {
		this.author=author;
	}
	
	void printInfo() {
		System.out.println("책 이름 : "+title);
		System.out.println("페이지 수 : "+page);
		System.out.println("저자 : "+author);
	}
}

class Magazine extends Book{
	private String date;
	
	Magazine(String title, int pageCount, String author,
			String date) {
		super(title, pageCount, author);
		this.date=date;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	void printInfo() {
		// TODO Auto-generated method stub
		super.printInfo();
		System.out.println("발매일 : "+date);
	}
}

public class Q3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		3. 일반적인 책을 나타내는 Book 클래스를 상속받아서 잡지를 나타내는 Magazine 클래스
//		를 작성하여 보자. Book 클래스는 제목, 페이지수, 저자 등의 정보를 가진다. Magazine
//		클래스는 추가로 발매일 정보를 가진다. 생성자, 접근자, 설정자를 포함하여서 각각의 클
//		래스를 작성한다. 이들 클래스들의 객체를 만들고 각 객체의 모든 정보를 출력하는 테스
//		트 클래스를 작성하라.
		Book b1 = new Book("사탄탱고", 412, "2018-05-09");
		Magazine m2 = new Magazine("아무도 오지 않는 곳에서", 300,"천선란", "2025-10-27");
		
		b1.printInfo();
		System.out.println();
		m2.printInfo();
	}

}
