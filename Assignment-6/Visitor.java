import tester.Tester;

public class Visitors {

}


interface IArith {
	<R> R accept(IArithVisitor<R> visitor); 
}

class Const implements IArith {
	double num;
	
	Const(double num) {
		this.num = num;
	}
	
	public <R> R accept(IArithVisitor<R> visitor) {
		return visitor.visitConst(this);
	}
}

class Formula implements IArith {
	IFunc2<Double, Double, Double> fun;
	String name;
	IArith left;
	IArith right;
	
	Formula(IFunc2<Double, Double, Double> fun, String name, IArith left, IArith right) {
		this.fun = fun;
		this.name = name;
		this.left = left;
		this.right = right;
	}
	
	public <R> R accept(IArithVisitor<R> visitor) {
		return visitor.visitFormula(this);
	}
}

interface IFunc2<A1, A2, R> {
	R apply(A1 a1, A2 a2);
}

interface IArithVisitor<R> {
	R visitConst(Const that);
	R visitFormula(Formula that);
}

class EvalVisitor implements IArithVisitor<Double> {
	public Double visitConst(Const that) {
		return that.num;
	}
	public Double visitFormula(Formula that) {
		return that.left.accept(this) * that.right.accept(this);
	}
}

class PrintVisitor implements IArithVisitor<String> {
	public String visitConst(Const that) {
		return Double.toString(that.num);
	}
	public String visitFormula(Formula that) {
		return "(" + that.name + " " + that.left.accept(this) + " " + that.right.accept(this) + ")";
	}
}
class DoubleVisitor implements IArithVisitor<IArith> {
	public IArith visitConst(Const that) {
		return new Const(that.num * 2);
	}
	public IArith visitFormula(Formula that) {
		return new Formula(that.fun, that.name, that.left.accept(this), that.right.accept(this));
	}
}

class func implements IFunc2<Double, Double, Double> {
	public Double apply(Double a1, Double a2) {
		return a1 * a2;
	}
}


class ExampleVisitor {
	ExampleVisitor() {}
	
	IArith IAriath1 = new Formula(new func(), "Matt", new Const(10), new Const(12));
	IArith IArith2 = new Formula(new func(), "Matt", new Const(20), new Const(24));
	
	
	boolean test(Tester t) {
		return t.checkExpect(this.IAriath1.accept(new EvalVisitor() ), 120.0);
	}
	boolean testPrint(Tester t) {
		return t.checkExpect(this.IAriath1.accept(new PrintVisitor() ), "(Matt 10.0 12.0)");
	}
	boolean testDouble(Tester t) {
		return t.checkExpect(this.IAriath1.accept(new DoubleVisitor() ), IArith2);
	}
}
