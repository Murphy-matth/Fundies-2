public class Problem3 {
	
}

//to represent a list of Strings
interface ILoString {
	 String combine();
	 // Sorts the list in alphabetical order, case-insensitive
	 ILoString sort(ICompareStrings pred);
	 ILoString insert(String s, ICompareStrings pred);
	 boolean isSorted(ICompareStrings comp);
	 boolean isSortedHelp(String s, ICompareStrings comp);
	 ILoString interleave(ILoString s);
	 ILoString interleaveHelp(String s, ILoString los);
	 boolean isCons();
	 ILoString merge(ILoString los);
	 ILoString mergeHelp(String s, ILoString los);
	 ILoString reverse();
	 ILoString reverseHelp(ILoString acc);
	 boolean isDoubledList();
	 boolean isDoubledListHelp(String s);
}

//to represent an empty list of Strings
class MtLoString implements ILoString {
	 MtLoString(){}
	 
	 public String combine() {
	    return "";
	 }  
	 
	 public ILoString sort(ICompareStrings pred) {
		 return this;
	 }
	 public ILoString insert(String s, ICompareStrings pred) {
		 return new ConsLoString(s, this);
	 }
	 public boolean isSorted(ICompareStrings comp) {
		 return true;
	 }
	 public boolean isSortedHelp(String s, ICompareStrings comp) {
		 return true;
	 }
	 public ILoString interleave(ILoString s) {
		 return s;
	 }
	 public ILoString interleaveHelp(String s, ILoString los) {
		 return new ConsLoString(s, los);
	 }
	 public boolean isCons() {
		 return false;
	 }
	 public ILoString merge(ILoString los) {
		 return los;
	 }
	 public ILoString mergeHelp(String s, ILoString los) {
		 return new ConsLoString(s, los);
	 }
	 public ILoString reverse() {
		 return this;
	 }
	 public ILoString reverseHelp(ILoString acc) {
		 return acc;
	 }
	 public boolean isDoubledList() {
		 return false;
	 }
	 public boolean isDoubledListHelp(String s) {
		 return false;
	 }
}

//to represent a nonempty list of Strings
class ConsLoString implements ILoString {
	String first;
	ILoString rest;
 
	ConsLoString(String first, ILoString rest){
		this.first = first;
		this.rest = rest;  
	}
 
	public String combine(){
		return this.first.concat(this.rest.combine());
	}  
	
	public ILoString sort(ICompareStrings pred) {
		return this.rest.sort(pred).insert(this.first, pred);
	}
	
	public ILoString insert(String s, ICompareStrings pred) {
		if (pred.apply(this.first, s) < 0) {
			return new ConsLoString(this.first, this.rest.insert(s, pred));
		}
		else {
			return new ConsLoString(s, this);
		}
	}
	public boolean isSorted(ICompareStrings comp) {
		return this.rest.isSortedHelp(this.first, comp);
	}
	public boolean isSortedHelp(String s, ICompareStrings comp) {
		if (comp.apply(s, this.first) < 0) {
			return this.rest.isSortedHelp(this.first, comp);
		}
		else {
			return false;
		}
	}
	public ILoString interleave(ILoString s) {
		return s.interleaveHelp(this.first, this.rest);
	}
	public ILoString interleaveHelp(String s, ILoString los) {
		return new ConsLoString(s, new ConsLoString(this.first, los.interleave(this.rest)));
	}
	 public boolean isCons() {
		 return true;
	 }
	 public ILoString merge(ILoString los) {
		 return mergeHelp(this.first, this.rest);
	 }
	 public ILoString mergeHelp(String s, ILoString los) {
		 if (s.toLowerCase().compareTo(this.first.toLowerCase()) < 0) {
			 return new ConsLoString(s, los.merge(this));
		 }
		 else {
			 return new ConsLoString(this.first, new ConsLoString(s, los).merge(this.rest));
		 }
	 }
	 public ILoString reverse() {
		 return reverseHelp(new MtLoString());
	 }
	 public ILoString reverseHelp(ILoString acc) {
		 return this.rest.reverseHelp(new ConsLoString(this.first, acc));
	 }
	 public boolean isDoubledList() {
		 return this.rest.isDoubledListHelp(this.first);
	 }
	 public boolean isDoubledListHelp(String s) {
		 if (s.compareTo(this.first) == 0) {
			 return true;
		 }
		 else {
			 return this.rest.isDoubledList();
		 }
	 }
}

interface ICompareStrings {
	int apply(String s1, String s2);
}

class compareByalphabet implements ICompareStrings {
	public int apply(String s1, String s2) {
		return s1.toLowerCase().compareTo(s2.toLowerCase());
	}
}



