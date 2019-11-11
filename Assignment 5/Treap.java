import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {
	private class Node<E> {
		// Data fields for Node class.
		public E data;
		public int priority;
		public Node<E> left;
		public Node<E> right;
		
		//Constructor
		/**
		 * 
		 * @param data
		 * @param priority
		 */
		public Node(E data, int priority) {
			if (data == null) {
				throw new IllegalArgumentException();
			} else {
				this.data = data;
				this.priority = priority;
				this.left = null;
				this.right = null;
			}
		}
		
		//Methods
		/**
		 * rotate the Treap right and update data
		 * @return
		 */
		public Node<E> rotateRight() {
			Node<E> temp = this.left;
			Node<E> left = temp.right;
			temp.right = this;
			this.left = left;
			return temp;
		}
		
		/**
		 * rotate the Treap left and update data
		 * @return
		 */
		public Node<E> rotateLeft() {
			Node<E> temp = this.right;
			Node<E> right = temp.left;
			temp.left = this;
			this.right = right;
			return temp;
		}
		
		public String toString() {
			return this.data.toString();
		}
	}
	
	// Data fields for Treap.
	private Random priorityGenerator;
	private Node<E> root;
	
	/**
	 * Create an empty treap
	 */
	public Treap() {
		priorityGenerator = new Random();
		root = null;
	}
	
	/**
	 * 
	 * @param seed
	 */
	public Treap(long seed) {
		priorityGenerator = new Random(seed);
		root = null;
	}
	
	//Treap class Methods.
	/**
	 * 
	 * @param child
	 * @param trace
	 */
	public void reheap(Node<E> child, Stack<Node<E>> trace) {
		while (!trace.isEmpty()) {
			Node<E> parent = trace.pop();
			if (parent.priority < child.priority) {
				if (parent.data.compareTo(child.data) > 0) {
					child = parent.rotateRight();
				} else {
					child = parent.rotateLeft();
				}
				if (!trace.isEmpty()) {
					if (trace.peek().left == parent) {
						trace.peek().left = child;
					} else {
						trace.peek().right = child;
					}
				} else { 
					this.root = child;
				}
			} else {
				break;
			}
		}
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	boolean add(E key) {
		return add(key, priorityGenerator.nextInt());
	}
	
	/**
	 * 
	 * @param key
	 * @param priority
	 * @return
	 */
	boolean add(E key, int priority) {
		if (root == null) {
			root = new Node<E>(key, priority);
			return true;
		} else {
			Node<E> n = new Node<E>(key, priority);
			Stack<Node<E>> trace = new Stack<Node<E>>();
			Node<E> localroot = root;
			while (localroot != null) {
				localroot.data.compareTo(key);
				if (localroot.data.compareTo(key) == 0) {
					return false;
				} else {
					if (localroot.data.compareTo(key) < 0) {
						trace.push(localroot);
						if (localroot.right == null) {
							localroot.right = n;
							reheap(n, trace);
							return true;
						} else {
							localroot = localroot.right;
						}
					} else {
						trace.push(localroot);
						if (localroot.left == null) {
							localroot.left = n;
							reheap(n, trace);
							return true;
						} else {
							localroot = localroot.left;
						}
					}
				}
			}
			return false;
		}
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public boolean delete(E key) {
		if (root == null || find(key) == false) {
			return false;
		} else {
			root = delete(key, root);
			return true;
		}
	}
	
	// Helper for delete.
	/**
	 * 
	 * @param key
	 * @param localroot
	 * @return
	 */
	private Node<E> delete(E key, Node<E> localroot){
		if (localroot == null) {
			return localroot;
		} else {
			if (localroot.data.compareTo(key) < 0) {
				localroot.right = delete(key, localroot.right);
			} else {
				if (localroot.data.compareTo(key) > 0) {
					localroot.left = delete(key, localroot.left);
				} else {
					if (localroot.right == null) {
						localroot = localroot.left;
					} else if (localroot.left == null) {
						localroot = localroot.right;
					} else {
						if (localroot.right.priority < localroot.left.priority) {
							localroot = localroot.rotateRight();
							localroot.right = delete(key, localroot.right);
						} else {
							localroot = localroot.rotateLeft();
							localroot.left = delete(key, localroot.left);
						}
					}
				}
			}
		}
		return localroot;
	}
	
	/**
	 * 
	 * @param root
	 * @param key
	 * @return
	 */
	public boolean find(Node<E> root,E key) {
		if(root==null) {
			return false;
		}
		if(key.compareTo(root.data) == 0) {
			return true;
		} else if(key.compareTo(root.data) < 0) {
			return find(root.left,key);
		} else {
			return find(root.right,key);
		}
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public boolean find(E key) {
		return find(root, key);
	}
	
	/**
	 * 
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		preOrderTraverse(root, 1, sb);
		return sb.toString();
	}
	
	//Helper for toString.
	/**
	 * 
	 * @param node
	 * @param depth
	 * @param sb
	 */
	private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
		for (int i = 1; i < depth; i++) {
			sb.append("  ");
		}
		if (node == null) {
			sb.append("null\n");
		} 
		else {
			sb.append("(key=" + node.toString() + ", ");
			sb.append("priority=");
			sb.append(node.priority);
			sb.append(")");
			sb.append("\n");
			preOrderTraverse(node.left, depth + 1, sb);
			preOrderTraverse(node.right, depth + 1, sb);
		}
	}
	
	public static void main(String[] args) {
		Treap<Integer> testTree = new Treap<Integer>();
		testTree.add(4,19); 
		testTree.add(2,31);
		testTree.add(6,70); 
		testTree.add(1,84);
		testTree.add(3,12); 
		testTree.add(5,83);
		testTree.add(7,26);
		System.out.println(testTree.delete(1));
		System.out.println(testTree.find(5));
		System.out.println(testTree.toString());
	}
}