import tester.*;
public class Files {

}
//to represent different files in a computer
interface IFile {
 
	 // compute the size of this file
	 int size();
	 
	 // compute the time (in seconds) to download this file
	 // at the given download rate
	 int downloadTime(int rate);
	 
	 // is the owner of this file the same 
	 // as the owner of the given file?
	 boolean sameOwner(IFile that);
	 boolean sameOwnerHelp(String s);
}


abstract class AFile implements IFile {
	String name;
	String owner;
	
	AFile(String name, String owner) {
		this.name = name;
		this.owner = owner;
	}
	
	 // is the owner of this file the same 
	 // as the owner of the given file?
	 public boolean sameOwner(IFile that) {
	     return that.sameOwnerHelp(this.owner);
	 }
	 public boolean sameOwnerHelp(String s) {
		 if (this.owner.compareTo(s) == 0) {
			 return true;
		 }
		 else {
			 return false;
		 }
	 }
	 
	 abstract public int size();
	 abstract public int downloadTime(int rate);
}
//to represent a text file
class TextFile extends AFile {
	 int length;   // in bytes
	 
	 TextFile(String name, String owner, int length) {
	     super(name, owner);
	     this.length = length;
	 }
	 
	 // compute the size of this file
	 public int size() {
	     return this.length;
	 }  
	 
	 // compute the time (in seconds) to download this file
	 // at the given download rate
	 public int downloadTime(int rate) {
	     return this.length / rate;
	 }
	 

}

//to represent an image file
class ImageFile extends AFile {
	 int width;   // in pixels
	 int height;  // in pixels
	 
	 ImageFile(String name, String owner, int width, int height) {
		 super(name, owner);
	     this.width = width;
	     this.height = height;
	 }
	 
	 // compute the size of this file
	 public int size() {
	     return this.width * this.height;
	 }  
	 
	 // compute the time (in seconds) to download this file
	 // at the given download rate
	 public int downloadTime(int rate) {
	     return this.size() / rate;
	 }
	
}


//to represent an audio file
class AudioFile extends AFile {
	 int speed;   // in bytes per second
	 int length;  // in seconds of recording time
	 
	 AudioFile(String name, String owner, int speed, int length) {
		 super(name, owner);
	     this.speed = speed;
	     this.length = length;
	 }
	 
	 // compute the size of this file
	 public int size() {
	     return this.speed * this.length;
	 }  
	 
	 // compute the time (in seconds) to download this file
	 // at the given download rate
	 public int downloadTime(int rate) {
	     return (this.speed * this.length) / rate;
	 }
	 
}

class ExamplesFiles {
 
	 IFile text1 = new TextFile("English paper", "Maria", 1234);
	 IFile picture = new ImageFile("Beach", "Maria", 400, 200);
	 IFile song = new AudioFile("Help", "Pat", 200, 120);
	 
	 // test the method size for the classes that represent files
	 boolean testSize(Tester t) {
	     return
	         t.checkExpect(this.text1.size(), 1234) &&
	         t.checkExpect(this.picture.size(), 80000) &&
	         t.checkExpect(this.song.size(), 24000) &&
	         t.checkExpect(this.text1.sameOwner(this.picture), true) &&
	         t.checkExpect(this.text1.sameOwner(this.song), false);
	 }
}
