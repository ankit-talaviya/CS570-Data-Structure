package dsassignment3;

public class IDLListTest {

	public static void main(String[] args) {
		IDLList<Integer> myList= new IDLList<Integer>();
		myList.add(4);
		myList.add(3);
		myList.add(2);
		myList.add(1);
		myList.add(0);
		System.out.println(myList.remove());
		myList.append(5);
		myList.add(4, 6);
		
		System.out.println(myList.removeAt(3));
		System.out.println(myList.remove(1));
		System.out.println(myList.removeLast());
		System.out.println(myList.toString());
		System.out.println(myList.get(1));
		System.out.println(myList.getHead());
		System.out.println(myList.getLast());
	}

}
