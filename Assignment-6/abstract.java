
public class ab {

}

interface IComparator<T> {
	int compare(T t1, T t2);
}

abstract class ABST<T> {
	IComparator<T> order;
	
	ABST(IComparator<T> order) {
		this.order = order;
	}
	
	abstract ABST<T> insert(T item);
	abstract T getLeftmost();
	abstract boolean isLeaf();
	abstract ABST<T> getRight();
	abstract boolean sameTree(ABST<T> that);
	boolean sameNode(Node<T> that) {
		return false;
	}
	boolean sameLeaf(Leaf<T> that) {
		return false;
	}
	abstract boolean sameData(ABST<T> that);
}


class Leaf<T> extends ABST<T> {
	Leaf(IComparator<T> order) {
		super(order);
	}
	
	public ABST<T> insert(T item) {
		return new Node<T>(this.order, item, this, this);
	}
	
	T getLeftmost() {
		throw new RuntimeException("No leftmost item of an empty tree");
	}
	
	public boolean isLeaf() {
		return true;
	}
	
	ABST<T> getRight() {
		throw new RuntimeException("No right of an empty tree");
	}
	
	public boolean sameTree(ABST<T> that) {
		return that.sameLeaf(this);
	}
	
	public boolean sameLeaf(Leaf<T> that) {
		return true;
	}
	
	public boolean sameData(ABST<T> that) {
		return that.sameLeaf(this);
	}
}

class Node<T> extends ABST<T> {
	T data;
	ABST<T> left;
	ABST<T> right;
	
	Node(IComparator<T> order, T data, ABST<T> left, ABST<T> right) {
		super(order);
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	public ABST<T> insert(T item) {
		if(this.order.compare(item, this.data) < 0) {
			return new Node<T>(this.order, this.data, this.left.insert(item), this.right);
		}
		else {
			return new Node<T>(this.order, this.data, this.left, this.right.insert(item));
		}
	}
	
	T getLeftmost() {
		if(this.left.isLeaf()) {
			return this.data;
		}
		else {
			return this.left.getLeftmost();
		}
	}
	
	public boolean isLeaf() {
		return false;
	}
	
	ABST<T> getRight() {
		if(this.left.isLeaf()) {
			return new Node<T>(this.order, this.data, new Leaf<T>(this.order), this.right);
		}
		else {
			return this.left.getRight();
		}
	}
	
	public boolean sameTree(ABST<T> that) {
		return that.sameNode(this);
	}
	
	public boolean sameNode(Node<T> that) {
		return this.data == that.data &&
				this.left.sameTree(that.left) &&
				this.right.sameTree(that.right);
	}
	
	public boolean sameData(ABST<T> that) {
		if (!that.isLeaf()) {
			if(this.getLeftmost() == that.getLeftmost()) {
				return this.getRight().sameData(that.getRight());
			}
			return false;
		}
		else {
			return false;
		}
	}
	
}

class Book {
	String title;
	String author;
	int price;
	
	Book(String title, String author, int price) {
		this.title = title;
		this.author = author;
		this.price = price;
	}
}

class BooksByTitle implements IComparator<Book> {
	public int compare(Book b1, Book b2) {
		return b1.title.compareTo(b2.title);
	}
}

class BooksByAuthor implements IComparator<Book> {
	public int compare(Book b1, Book b2) {
		return b1.author.compareTo(b2.author);
	}
}

class BooksByPrice implements IComparator<Book> {
	public int compare(Book b1, Book b2) {
		return b1.price - b2.price;
	}
}



