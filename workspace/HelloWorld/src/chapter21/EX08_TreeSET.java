package chapter21;

import java.util.TreeSet;

public class EX08_TreeSET {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeSet<String> tree = new TreeSet<>();
		tree.add("홍길동");
		tree.add("전우치");
		tree.add("손오공");
		tree.add("멀린");
		tree.add("손오공");
		
		System.out.println("객체 수: "+tree.size());
		
		for(String t : tree) {
			System.out.println(t);
		}
		
		System.out.println();
		
		TreeSet<Integer> numTree = new TreeSet<>();
//		중복 제거, 숫자의 경우 크기순을 오름차순 정렬됨
		numTree.add(5);
		numTree.add(2);
		numTree.add(3);
		numTree.add(1);
		numTree.add(4);
		numTree.add(5);
		numTree.add(2);
		numTree.add(3);
		numTree.add(1);
		numTree.add(4);
		
		System.out.println("객체 수: "+numTree.size());
		
		for(int t : numTree) {
			System.out.println(t);
		}
	}

}
