import tester.Tester;


public class Dequeue {

}
interface IPred<T> {
	  boolean apply(T t);
	  
}


abstract class ANode<T> {
	ANode<T> next;
	ANode<T> prev;
	
	abstract int size();
	abstract void addAtHead(T item);
	abstract void addAtTail(T item);
	abstract void removeFromHead();
	abstract void remove();
	abstract void removeFromTail();
	abstract ANode<T> find(IPred<T> pred);
	abstract void removeNode(ANode<T> node);
	
}

class Node<T> extends ANode<T>{
	T data;
	
	Node(T data) {
		this.data = data;
		this.next = null;
		this.prev = null;
	}
	
	Node(T data, ANode<T> next, ANode<T> prev) {
		if (next == null || prev == null) {
			throw new IllegalArgumentException("Nodes cannot be null");
		}
		this.data = data;
		this.next = next;
		this.prev = prev;
		next.prev = this;
		prev.next = this;
	}
	
	public int size() {
		return 1 + this.next.size();
	}
	
	public void addAtHead(T item) {
		this.next.addAtHead(item);
	}
	public void addAtTail(T item) {
		this.next.addAtTail(item);
	}
	public void removeFromHead() {
		if (this.prev != null) {
			this.prev.removeFromHead();
		}
	}
	public void remove() {
		this.prev.next = this.next;
		this.next.prev = this.prev;
	}
	public void removeFromTail() {
		if (this.next != null) {
			this.next.removeFromTail();
		}
	}
	public ANode<T> find(IPred<T> pred) {
		if(pred.apply(this.data)) {
			return this;
		}
		else {
			return this.next.find(pred);
		}
	}
	public void removeNode(ANode<T> node) {
		if (this.equals(node)) {
			this.remove();
		}
		else {
			this.removeNode(this.next);
		}
	}
}

class Sentinel<T> extends ANode<T>{
	Sentinel() {
		this.prev = this;
		this.next = this;
	}
	
	public int size() {
		return 0;
	}
	
	public void addAtHead(T item) {
		this.next = new Node<T>(item, this.next, this);
	}
	public void addAtTail(T item) {
		this.prev.next = new Node<T>(item, this, this.prev);
	}
	public void removeFromHead() {
			this.next.remove();
	}
	public void remove() {
		throw new RuntimeException("Cannot remove an item from an empty list");
	}
	public void removeFromTail() {
		this.prev.remove();
	}
	public ANode<T> find(IPred<T> pred) {
		return this;
	}
	public void removeNode(ANode<T> node) {
		
	}
}

class Deque<T> {
	Sentinel<T> header;
	
	Deque() {
		this.header = new Sentinel<T>();
	}
	Deque(Sentinel<T> header) {
		this.header = header;
	}
	
	public int size() {
		return this.header.next.size();
	}
	
	public void addAtHead(T item) {
		this.header.next.addAtHead(item);
	}
	
	public void addAtTail(T item) {
		this.header.next.addAtTail(item);
	}
	
	public void removeFromHead() {
		this.header.next.removeFromHead();
	}
	
	public void removeFromTail() {
		this.header.next.removeFromTail();
	}
	
	public ANode<T> find(IPred<T> pred) {
		return this.header.next.find(pred);
	}
	
	public void removeNode(ANode<T> node) {
		this.header.next.removeNode(node);
	}
}




class ExampleDeque {
	ExampleDeque() {}
	
	Deque<String> deque1 = new Deque<String>();
	Deque<String> deque2 = new Deque<String>(new Sentinel<String>());
	Deque<String> deque3 = new Deque<String>(new Sentinel<String>());
	
	
	boolean test(Tester t) {
		deque2.addAtHead("def");
		deque2.addAtHead("cde");
		deque2.addAtHead("bcd");
		deque2.addAtHead("acd");
		
		return t.checkExpect(deque2, deque2);
	}
	
}





