// Lecture 5
interface IPred <T> {
	boolean apply(T arg);
}

interface IComparator <T> {
	int compare(T t1, T t2);
}

interface IList <T> {
	IList<T> filter(IPred<T>pred);
	IList<T> sort(IComparator<T> comp);
	IList<T> insertBy(IComparator<T> comp, T t);
	int length();
}


class MtList<T> implements IList<T> {
	public IList<T> filter(IPred<T> pred) { return this; }
    public IList<T> sort(IComparator<T> comp) { return this; }
    public IList<T> insertBy(IComparator<T> comp, T t) {
    	return new ConsList<T>(t, this);
    }
	public int length() { return 0; }
}

class ConsList<T> implements IList<T> {
	T first;
	IList<T> rest;
	ConsList(T first, IList<T> rest) {
		this.first = first;
		this.rest = rest;
	}
	
	public int length() {
		return 1 + rest.length();
	}
	
	public IList<T> filter(IPred<T> pred) {
		if (pred.apply(this.first)) {
			return new ConsList<T>(this.first, this.rest.filter(pred));
		}
		else {
			return this.rest.filter(pred);
		}
	}
	
	public IList<T> sort(IComparator<T> comp) {
		return this.rest.sort(comp).insertBy(comp, this.first);
	}
	
	public IList<T> insertBy(IComparator<T> comp, T t) {
		if (comp.compare(this.first, t) < 0) {
			return new ConsList<T>(this.first, this.rest.insertBy(comp, t));
		}
		else {
			return new ConsList<T>(t, this);
		}
	}
}
