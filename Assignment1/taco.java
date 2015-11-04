interface ITaco {
	
}


public class taco {

}

class EmptyShell implements ITaco {
	boolean softshell;
	
	EmptyShell(boolean softshell) {
		this.softshell = softshell;
	}
}

class Filled implements ITaco {
	ITaco taco;
	String filled;
	
	Filled(ITaco taco, String filled) {
		this.taco = taco;
		this.filled = filled;
	}
}


class Example {
	Example() {}
	
	ITaco ss1 = new EmptyShell(true);
	ITaco F1 = new Filled(this.ss1, "cheddar cheese");
	ITaco f2 = new Filled(this.F1, "lettuce");
	ITaco F3 = new Filled(this.f2, "salsa");
	ITaco F4 = new Filled(this.F3, "carnitas");
}

