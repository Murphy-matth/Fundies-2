import java.util.*;
import java.math.*;
import tester.Tester;

class Heap<T> {
	ArrayList<T> data;
	/*
	 * The if the first value is larger returns > 0
	 */
	IComparator<T> comp;
	int count;
	
	Heap(IComparator<T> comp) {
		this.data = new ArrayList<T>();
		this.comp = comp;
		this.count = 0;
	}
	
	Heap(ArrayList<T> data, IComparator<T> comp) {
		this.data = data;
		this.comp = comp;
		this.count = this.data.size();
		this.buildHeap();
	}
	
	public int getParent(int index) {
		return (int)Math.floor(((index - 1) / 2));
	}
	
	public int leftChild(int index) {
		return (2 * index) + 2;
	}
	public int rightChild(int index) {
		return (2 * index) + 1;
	}
	
	/*
	 * Insert an item into the heap, adds to the end and bubbles it up
	 */
	public void insert(T item) {
		this.add(item);
		this.bubbleUp();
	}
	
	/*
	 * Add an item into the heap
	 */
	private void add(T item) {
		this.data.add(this.count, item);
		this.count++;
	}
	
	/*
	 * Swap the value at Index 1 with the value at index 2
	 */
	private void swap(int index1, int index2) {
		T temp = this.data.get(index2);
		this.data.set(index2, this.data.get(index1));
		this.data.set(index1, temp);
	}
	
	/*
	 * Moves the value added at the end to the correct spot in the heap
	 */
	private void bubbleUp() {
		int pos = this.count - 1;
		while(pos > 0 && this.comp.compare(this.data.get(pos), this.data.get(this.getParent(pos))) > 0) {
			this.swap(pos, this.getParent(pos));
			pos = this.getParent(pos);
		}
	}
	
	public T extract() {
		T item = this.data.get(0);
		this.swap(0, this.count - 1);
		this.count--;
		this.maxHeapify(0);
		return item;
	}
	
	public void maxHeapify(int index) {
		int left = this.leftChild(index);
		int right = this.rightChild(index);
		int largest = index;
		if (left <= this.count - 1 && this.comp.compare(this.data.get(left), this.data.get(largest)) > 0) {
			largest = left;
		}
		if (right <= this.count - 1 && this.comp.compare(this.data.get(right), this.data.get(largest)) > 0) {
			largest = right;
		}
		if (largest != index) {
			this.swap(index, largest);
			this.maxHeapify(largest);
		}
	}
	
	public void buildHeap() {
		for (int i = this.count - 1; i >= 0; i--) {
			this.maxHeapify(i);
		}
	}
	
	public ArrayList<T> print() {
		return this.data;
	}
	
	public ArrayList<T> heapSort() {
		ArrayList<T> output = new ArrayList<T>();
		Heap<T> temp = new Heap<T>(this.data, this.comp);
		for(int i = 0; i < this.count; i ++) {
			output.add(temp.extract());
		}
		return output;
	}
}

interface IComparator <T> {
	int compare(T t1, T t2);
}

class compareHeap implements IComparator<Integer> {
	public int compare(Integer t1, Integer t2) {
		return t1 - t2;
	}
}


class ExampleHeap {
	ExampleHeap() {}
	
	ArrayList<Integer> data = new ArrayList<Integer>();
	compareHeap comp = new compareHeap();
	Heap<Integer> myHeap = new Heap<Integer>(this.comp);
	
	boolean test(Tester t) {
		this.myHeap.insert(1);
		this.myHeap.insert(2);
		this.myHeap.insert(3);
		this.myHeap.insert(20);
		this.myHeap.insert(4);
		this.myHeap.insert(5);
		this.myHeap.buildHeap();
		
		ArrayList<Integer> hint = new ArrayList<Integer>();
		hint.add(20);
		hint.add(5);
		hint.add(4);
		hint.add(3);
		hint.add(2);
		hint.add(1);
		
		
		return t.checkExpect(this.myHeap.heapSort(), hint);
		
	}
	
}

