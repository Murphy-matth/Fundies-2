import tester.Tester;

public class Registrar {

}

class ExampleRegistrar {
	ExampleRegistrar() {}
	

	
	
	
	boolean test(Tester t) {
		Instructor bob = new Instructor("Bob");
		Instructor matt = new Instructor("Matt");
		Student jon = new Student("Jon", 1);
		Student kun = new Student("Kun", 2);
		Student jed = new Student("Jed", 3);
		Course calc1 = new Course("Calc1", bob, new MtList<Student>());
		Course calc2 = new Course("Calc2", matt, new MtList<Student>());
		Course calc3 = new Course("Calc3", bob, new MtList<Student>());
		jon.enroll(calc1);
		jon.enroll(calc2);
		jon.enroll(calc3);
		kun.enroll(calc1);
		bob.enroll(calc1);
		bob.enroll(calc3);
		matt.enroll(calc2);
		jed.enroll(calc3);
		return t.checkExpect(calc2.students, new ConsList<Student>(jon, new MtList<Student>())) &&
				t.checkExpect(calc1.students, new ConsList<Student>(kun,new ConsList<Student>(jon, new MtList<Student>()))) &&
				t.checkExpect(kun.courses, new ConsList<Course>(calc1, new MtList<Course>())) &&
				t.checkExpect(jon.courses, new ConsList<Course>(calc3, new ConsList<Course>(calc2, new ConsList<Course>(calc1, new MtList<Course>())))) &&
				t.checkExpect(calc1.equals(calc3), false) &&
				t.checkExpect(calc1.equals(calc1), true) &&
				t.checkExpect(jon.courses.overlapping(jon.courses), true) &&
				t.checkExpect(calc1.equals(calc1), true) &&
				t.checkExpect(calc1.equals(calc3), false) &&
				t.checkExpect(jon.courses.overlapping(kun.courses), true) && 
				t.checkExpect(kun.courses.overlapping(jed.courses), false) &&
				t.checkExpect(bob.dejavu(jon), true) &&
				t.checkExpect(bob.dejavu(jed), false) &&
				t.checkExpect(matt.dejavu(kun), false) &&
				t.checkExpect(matt.courses.overlappingCount(kun.courses, 0), 0);
	}
}

class Course {
	String name;
	Instructor instructor;
	IList<Student> students;
	
	Course(String name, Instructor instructor, IList<Student> students) {
		if(instructor == null) {
			throw new RuntimeException("This course needs an instructor to teach it");
		}
		else {
			this.name = name;
			this.instructor = instructor;
			this.students = students;
		}
	}
	
	public void enroll(Student S) {
		this.students = new ConsList<Student>(S, this.students);
	}
}

class Instructor {
	String name;
	IList<Course> courses;
	
	Instructor(String name) {
		this.name = name;
		this.courses = new MtList<Course>();
	}
	public boolean dejavu(Student C) {
		if (this.courses.overlappingCount(C.courses, 0) > 1){
			return true;
		}
		else {
			return false;
		}
	}
	public void enroll(Course C) {
		this.courses = new ConsList<Course>(C, this.courses);
	}
	public boolean sameInstructor(Instructor that) {
		if(this.name == that.name) {
			return true;
		}
		else {
			return false;
		}
	}
}

class Student {
	String name;
	int id;
	IList<Course> courses;
	
	Student(String name, int id) {
		this.name = name;
		this.id = id;
		this.courses = new MtList<Course>();
	}
	
	public void enroll(Course C) {
		this.courses = new ConsList<Course>(C, this.courses);
		C.enroll(this);
	}
	
	public boolean classmates(Student c) {
		return this.courses.overlapping(c.courses);
	}
}







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
	boolean contains(T item);
	boolean overlapping(IList<T> that);
	boolean overlappingMtList(MtList<T> that);
	boolean overlappingConsList(ConsList<T> that);
	int overlappingCount(IList<T> that, int acc);
	int overlappingCountMtList(MtList<T> that, int acc);
	int overlappingCountConsList(ConsList<T> that, int acc);
}


class MtList<T> implements IList<T> {
	public IList<T> filter(IPred<T> pred) { return this; }
    public IList<T> sort(IComparator<T> comp) { return this; }
    public IList<T> insertBy(IComparator<T> comp, T t) {
    	return new ConsList<T>(t, this);
    }
	public int length() { return 0; }
	public boolean contains(T item) { return false; }
	public boolean overlapping(IList<T> that) {
		return that.overlappingMtList(this);
	}
	public boolean overlappingMtList(MtList<T> that) {
		return false;
	}
	public boolean overlappingConsList(ConsList<T> that) {
		return false;
	}
	public int overlappingCount(IList<T> that, int acc) {
		return that.overlappingCountMtList(this, acc);
	}
	public int overlappingCountMtList(MtList<T> that, int acc) {
		return acc;
	}
	public int overlappingCountConsList(ConsList<T> that, int acc) {
		return acc;
	}
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
	public boolean contains(T item) {
		if (this.first == item) {
			return true;
		}
		else {
			return this.rest.contains(item);
		}
	}
	public boolean overlapping(IList<T> that) {
		return that.overlappingConsList(this);
	}
	public boolean overlappingMtList(MtList<T> that) {
		return false;
	}
	public boolean overlappingConsList(ConsList<T> that) {
		if (this.contains(that.first)) {
			return true;
		}
		else {
			return that.rest.overlapping(this);
		}
	}
	public int overlappingCount(IList<T> that, int acc) {
		return that.overlappingCountConsList(this, acc);
	}
	public int overlappingCountMtList(MtList<T> that, int acc) {
		return acc;
	}
	public int overlappingCountConsList(ConsList<T> that, int acc) {
		if (this.contains(that.first)) {
			return that.rest.overlappingCount(this, acc + 1);
		}
		else {
			return that.rest.overlappingCount(this, acc);
		}
	}
}
