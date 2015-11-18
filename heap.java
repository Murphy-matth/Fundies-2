import java.util.*;
import java.math.*;
import tester.Tester;

class Heap<T> {
	ArrayList<T> data;
	Comparator<T> comp;
	int count;
	
	/*
	 * Construct an empty heap
	 */
	Heap(Comparator<T> comp) {
		this.data = new ArrayList<T>();
		this.comp = comp;
		this.count = 0;
	}
	
	/*
	 * Construct a heap based on the ArrayList of data and the
	 * comparator
	 */
	Heap(ArrayList<T> data, Comparator<T> comp) {
		this.data = new ArrayList<T>(data);
		this.comp = comp;
		this.count = this.data.size();
		this.buildHeap();
	}
	
	/*
	 * Returns the parent index
	 * Will return index's out of the bounds of the ArrayList
	 */
	public int getParent(int index) {
		return (int)Math.floor(((index - 1) / 2));
	}
	
	/*
	 * Returns the left child index
	 * Will return index's out of the bounds of the ArrayList
	 */
	public int leftChild(int index) {
		return (2 * index) + 2;
	}
	
	/*
	 * Returns the right child index
	 * Will return index's out of the bounds of the ArrayList
	 */
	public int rightChild(int index) {
		return (2 * index) + 1;
	}
	
	/*
	 * Insert an item into the heap
	 * adds to the end and bubbles it up
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
	
	/*
	 * Returns the first element in the heap
	 * Removes it from the heap by placing it at the end
	 * And decreasing the count
	 */
	public T extract() {
		T item = this.data.get(0);
		this.swap(0, this.count - 1);
		this.count--;
		this.Heapify(0);
		return item;
	}
	
	/*
	 * Builds a heap from this index
	 */
	public void Heapify(int index) {
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
			this.Heapify(largest);
		}
	}
	
	/*
	 * Creates the heap from the ArrayList
	 * by calling build heap for all the indices
	 * in the ArrayList
	 * starting from the end
	 */
	public void buildHeap() {
		for (int i = this.count - 1; i >= 0; i--) {
			this.Heapify(i);
		}
	}
	
	/*
	 * Dosen't actually print just returns the ArrayList
	 */
	public ArrayList<T> print() {
		return this.data;
	}
	
	/*
	 * Returns a sorted version of the heap
	 * as an ArrayList
	 * Note does not actually sort the heap
	 */
	public ArrayList<T> heapSort() {
		ArrayList<T> output = new ArrayList<T>();
		Heap<T> temp = new Heap<T>(this.data, this.comp);
		for(int i = 0; i < this.count; i ++) {
			output.add(temp.extract());
		}
		return output;
	}
}

class heapCompare implements Comparator<Integer> {
	public int compare(Integer t1, Integer t2) {
		return t1 - t2;
	}
}


class ExampleHeap {
	ExampleHeap() {}
	
	ArrayList<Integer> data = new ArrayList<Integer>();
	heapCompare comp = new heapCompare();
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
		ArrayList<Integer> hint1 = new ArrayList<Integer>();
		hint.add(20);
		hint.add(5);
		hint.add(4);
		hint.add(3);
		hint.add(2);
		hint.add(1);
		Heap<Integer> myHeap1 = new Heap<Integer>(hint, this.comp);
		myHeap1.extract();
		hint1.add(5);
		hint1.add(3);
		hint1.add(4);
		hint1.add(1);
		hint1.add(2);
		hint1.add(20);
		
		
		return t.checkExpect(this.myHeap.heapSort(), hint) &&
				t.checkExpect(myHeap1.print(), hint1);
		
	}
	
}

