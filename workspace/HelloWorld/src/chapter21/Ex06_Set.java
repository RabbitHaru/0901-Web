package chapter21;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Ex06_Set {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Set 자료구조 선언하기
		Set<String> set = new HashSet<>();
//		add() : set에 데이터를 저장하는 메서드
		set.add("orange");
		set.add("apple");
		set.add("banana");
		set.add("apple");  // 중복 데이터는 저장되지 않음
		set.add("melon");
		set.add("pineapple");
		set.add("melon");
//		size() : Set에 저장된 자료주고 반복하기
		System.out.println("객체 수 : "+set.size());
		
//		Iterator를 통한 Set 자료구조 반복하기
		Iterator<String> itr = set.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next() + '\t');
//			set.remove(0); 순서가 없는 자료구조이기 때문에 인덱스로 삭제할 수 업음
//			set.remove("orange");
	
		}
		System.out.println();
		
		for(String s : set)
			System.out.println(s + '\t');
		System.out.println();

	}

}
