package TEST2;

import java.util.Scanner;

class Phone {
	private String name;
	private String tel;
	private String address;

	Phone(String name, String tel, String address) {
		this.name = name;
		this.tel = tel;
		this.address = address;
	}

	String getName() {
		return name;
	}

	String getTel() {
		return tel;
	}

	String getAddress() {
		return address;
	}
}

public class Q2_PhoneManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		System.out.print("인원수 >> ");
		int n = sc.nextInt();
		sc.nextLine();

		Phone[] phones = new Phone[n];

		for (int i = 0; i < n; i++) {
			System.out.print("이름과 전화번호(번호는 연속적으로 입력), 주소 >>");
			String line = sc.nextLine();
			String[] parts = line.split(" ", 3);
			String name = parts[0];
			String tel = parts[1];
			String address = parts[2];

			phones[i] = new Phone(name, tel, address);
		}
		System.out.println("저장되었습니다...");

		while (true) {
			System.out.print("검색할 이름>> ");
			String searchName = sc.nextLine();
			if (searchName.equals("exit")) {
				break;
			}
			boolean found = false;
			for (Phone p : phones) {
				if (p.getName().equals(searchName)) {
					System.out.println(p.getName() + "의 번호와 주소는 " + p.getTel() + ", " + p.getAddress() + " 입니다.");
					found = true;
					break;
				}
			}
			if (!found) {
				System.out.println(searchName + " 은(는) 없습니다.");
			}
		}
		System.out.println("프로그램을 종료합니다...");
		sc.close();
	}

}
