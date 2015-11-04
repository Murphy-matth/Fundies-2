
public class ExcelCells {

}


class Cell {
	IData data;
	String row;
	String column;
	
	Cell(IData data, String row, String column) {
		this.data = data;
		this.row = row;
		this.column = column;
	}
	
	public int value() {
		return this.data.value();
	}
	
	public int countArgs() {
		return this.data.countArgs();
	}
	
	public int countFuns() {
		return this.data.countFuns();
	}
	
	public int formulaDepth() {
		return this.data.formulaDepth();
	}
	
	public String formula(int depth) {
		if(depth == 0) {
			return this.getCord();
		}
		else {
			return this.data.formula(depth - 1);
		}
	}
	
	public String getCord() {
		return this.column + this.row;
	}
}


interface IData {
	int value();
	int countArgs();
	int countFuns();
	int formulaDepth();
	boolean isNumber();
	String formula(int depth);
}

class Number implements IData{
	int number;
	
	Number(int number) {
		this.number = number;
	}
	
	public int value() {
		return this.number;
	}
	
	public int countArgs() {
		return 1;
	}
	public int countFuns() {
		return 0;
	}
	public int formulaDepth() {
		return 0;
	}
	public boolean isNumber() {
		return true;
	}
	public String formula(int depth) {
		return Integer.toString(this.number);
	}
}

class Formula implements IData {
	function function;
	
	Formula(function function) {
		this.function = function;
	}
	
	public int value() {
		return this.function.apply();
	}
	
	public int countArgs() {
		return this.function.countArgs();
	}
	public int countFuns() {
		return this.function.countFuns();
	}
	public int formulaDepth() {
		return this.function.formulaDepth();
	}
	public boolean isNumber() {
		return false;
	}
	public String formula(int depth) {
		return this.function.formula(depth);
	}
}

interface function {
	int apply();
	int countArgs();
	int countFuns();
	int formulaDepth();
	String formula(int depth);
}

abstract class fun implements function {
	Cell a;
	Cell b;
	
	fun(Cell a, Cell b) {
		this.a = a;
		this.b = b;
	}
	
	abstract public int apply();
	public int countArgs() {
		return this.a.countArgs() + this.b.countArgs();
	}
	
	public int countFuns() {
		return this.a.countFuns() + this.b.countFuns();
	}
	public int formulaDepth() {
		int a = this.a.formulaDepth();
		int b = this.b.formulaDepth();
		if (a >= b) {
			return a;
		}
		else {
			return b;
		}
	}
	abstract public String formula(int depth);
	
}

class mul extends fun {
	Cell a;
	Cell b;
	mul (Cell a, Cell b) {
		super(a, b);
	}
	
	public int apply() {
		return this.a.value() * this.b.value();
	}
	public String formula(int depth) {
		if (depth == 0) {
			return "mul( " + this.a.getCord() + "," + this.b.getCord() + ")";
		}
		else {
			return "mul( " + this.a.formula(depth - 1) + ", " + this.b.formula(depth - 1) + ")";
		}
	}
}

class mod extends fun {
	Cell a;
	Cell b;
	mod(Cell a, Cell b) {
		super(a, b);
	}
	
	public int apply() {
		return this.a.value() / this.b.value();
	}
	public String formula(int depth) {
		if (depth == 0) {
			return "mod( " + this.a.getCord() + "," + this.b.getCord() + ")";
		}
		else {
			return "mod( " + this.a.formula(depth - 1) + ", " + this.b.formula(depth - 1) + ")";
		}
	}
}

class sub extends fun {
	Cell a;
	Cell b;
	sub(Cell a, Cell b) {
		super(a, b);
	}
	
	public int apply() {
		return this.a.value() - this.b.value();
	}
	public String formula(int depth) {
		if (depth == 0) {
			return "sub( " + this.a.getCord() + "," + this.b.getCord() + ")";
		}
		else {
			return "sub( " + this.a.formula(depth - 1) + ", " + this.b.formula(depth - 1) + ")";
		}
	}
}
