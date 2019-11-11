package dsassignment3;

import java.util.ArrayList;

public class IDLList<E> {
	
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices = new ArrayList<>();
	
	private static class Node<E> {
		
		E data;
		private Node<E> next = null;
		private Node<E> prev = null;
		
		Node (E elem) {
			this.data = elem;
		}
		
		Node (E elem, Node<E> prev, Node<E> next) {
			this.data = elem;
			this.prev = prev;
			this.next = next;
		}
	}
	
	//Creates an empty double-linked list
	public IDLList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	//Adds elem at position index
	public boolean add(int index, E elem) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> nd = indices.get(index);
		Node<E> tmp = new Node<E>(elem , nd.prev, nd);
		nd.prev.next = tmp;
		nd.prev = tmp;
		indices.add(index,tmp);
		size++;
		return true;
	}
	
	//Adds elem at the head
	public boolean add(E elem) {
		if(head == null) {
			Node<E> tmp = new Node<E>(elem);
			tmp.data = elem;
			head = tmp;
			tail = tmp;
			indices.add(head);
			size++;
			return true;
		} else {
			Node<E> tmp = new Node<E>(elem);
			tmp.next = head;
			head.prev = tmp;
			head = tmp;
			indices.add(0,tmp);
			size++;
			return true;
		}

	}
	
	//Adds elem as the new last element of the list
	public boolean append(E elem) {
		if(head == null) {
			head.data = elem;
			tail.data = elem;
			indices.add(head);
			size++;
			return true;
		} else {
			Node<E> tmp = new Node<E>(elem);
			tail.next = tmp;
			tmp.prev = tail;
			tail = tmp;
			indices.add(tail);
			size++;
			return true;
		}
	}
	
	//Returns the object at position index from the head
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return this.indices.get(index).data;
	}
	
	//Returns the object at the head
	public E getHead() {
		return head.data;
	}
	
	//Returns the object at the tail
	public E getLast() {
		return tail.data;
	}
	
	//Returns the list size
	public int size() {
		return size;
	}
	
	//Removes and returns the element at the head
	public E remove() {
		if (size == 0) {
			throw new IndexOutOfBoundsException();
		}
		if(tail == head) {
			Node<E> tmp = head;
			head = null;
			tail = null;
			size--;
			return tmp.data;
		}
		head = head.next;
		head.prev = null;
		size--;
		return indices.remove(0).data;
	}
	
	//Removes and returns the element at the tail
	public E removeLast() {
		if (size == 0) {
			throw new IndexOutOfBoundsException();
		}
		if(tail == head) {
			Node<E> tmp = head;
			head = null;
			tail = null;
			size--;
			return tmp.data;
		}
		tail = tail.prev;
		tail.next = null;
		size--;
		return indices.remove(size).data;
	}
	
	//Removes and returns the element at the index index
	public E removeAt(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if(index == 0) {
			E tmp = this.remove();
			return tmp;
		}
		Node<E> tmp = indices.get(index);
		tmp.prev.next = tmp.next;
		tmp.next.prev = tmp.prev;
		size--;
		return indices.remove(index).data;
	}
	
	//Removes the first occurrence of in the list and returns true
	public boolean remove(E elem) {
		Node<E> tmp = head;
		int i = 0;
		while(tmp.next != null){
			if(tmp.data.equals(elem)){
				if(i == 0) {
						head = head.next;
						size--;
						indices.remove(0);
						return true;
				}
				tmp.next.prev = tmp.prev;
				tmp.prev.next = tmp.next;
				size--;
				indices.remove(i);
				return true;
			}
			tmp = tmp.next;
			i++;
		}
		return false;
	}
	
	//Presents a string representation of the list
	public String toString() {
		Node<E> nodeRef = head;
		StringBuilder result = new StringBuilder();
		while (nodeRef != null) {
			result.append(nodeRef.data);
			if (nodeRef.next != null) {
				result.append(" --> ");
			}
			nodeRef = nodeRef.next;
		}
		return result.toString();
	}

}
