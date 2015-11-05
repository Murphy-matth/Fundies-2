
public class WebPage {
	String url;
	String title;
	ILoItem item;
	
	public WebPage(String url, String title, ILoItem item) {
		this.url = url;
		this.title = title;
		this.item = item;
	}
	
	public int totalImageSize() {
		return this.item.getsize();
	}
	
	public int textLength() {
		return this.title.length() + this.item.textLength();
	}
	
	public String images() {
		return this.item.images();
	}
	
	public boolean sameWebPage(WebPage that) {
		if (this.url == that.url && this.title == that.title && this.item.sameLoItem(that.item)) {
			return true;
		}
		else {
			return false;
		}
	}
}


interface ILoItem {
	int getsize();
	int textLength();
	String images();
	boolean isCons();
	boolean sameLoItem(ILoItem that);
	boolean sameMtLoItem(MtLoItem that);
	boolean sameConsLoItem(ConsLoItem that);
}

class MtLoItem implements ILoItem{
	MtLoItem() {}
	
	public int getsize() {
		return 0;
	}
	
	public int textLength() {
		return 0;
	}
	
	public String images() {
		return "";
	}
	
	public boolean isCons() {
		return false;
	}
	public boolean sameLoItem(ILoItem that) {
		return this.sameMtLoItem(this);
	}
	public boolean sameConsLoItem(ConsLoItem that) {
		return false;
	}
	public boolean sameMtLoItem(MtLoItem that) {
		return true;
	}
	
}

class ConsLoItem implements ILoItem{
	Item first;
	ILoItem rest;
	
	ConsLoItem(Item first, ILoItem rest) {
		this.first = first;
		this.rest = rest;
	}
	
	public int getsize() {
		return this.first.getsize() + this.rest.getsize();
	}
	
	public int textLength() {
		return this.first.textLength() + this.rest.textLength();
	}
	
	public String images() {
		if (this.rest.isCons()) {
			return this.first.images() + ", " + this.rest.images();
		}
		else {
			return this.first.images() + this.rest.images();
		}
	}
	
	public boolean isCons() {
		return true;
	}
	public boolean sameLoItem(ILoItem that) {
		return this.sameConsLoItem(this);
	}
	public boolean sameConsLoItem(ConsLoItem that) {
		if (this.first.sameItem(that.first)) {
			return this.rest.sameLoItem(that.rest);
		}
		else {
			return false;
		}
	}
	public boolean sameMtLoItem(MtLoItem that) {
		return false;
	}
}

interface Item {
	int getsize();
	int textLength();
	String images();
	boolean sameItem(Item that);
	boolean sameText(Text that);
	boolean sameImage(Image that);
	boolean sameLink(Link that);
	boolean sameParagraph(Paragraph that);
	boolean sameHeader(Header that);
}

abstract class AItem implements Item {
	public abstract int getsize();
	public abstract int textLength();
	public abstract String images();
	public abstract boolean sameItem(Item that);
	
	public boolean sameText(Text that) {
		return false;
	}
	public boolean sameImage(Image that) {
		return false;
	}
	public boolean sameLink(Link that) {
		return false;
	}
	public boolean sameParagraph(Paragraph that) {
		return false;
	}
	public boolean sameHeader(Header that) {
		return false;
	}
}

class Text extends AItem {
	String contents;
	
	Text(String contents) {
		this.contents = contents;
	}
	
	public int getsize() {
		return 0;
	}
	
	public int textLength() {
		return this.contents.length();
	}
	
	public String images() {
		return "";
	}
	public boolean sameItem(Item that) {
		return that.sameText(this);
	}
	public boolean sameText(Text that) {
		if (this.contents == that.contents) {
			return true;
		}
		else {
			return false;
		}
	}
}

class Paragraph extends Text {
	Paragraph(String contents) {
		super(contents);
	}
	
}

class Header extends Text {
	Header(String contents) {
		super(contents);
	}
	
}

class Image extends AItem {
	String filename;
	int size;
	String fileType;
	
	Image(String filename, int size, String fileType) {
		this.filename = filename;
		this.size = size;
		this.fileType = fileType;
	}
	
	public int getsize() {
		return this.size;
	}
	
	public int textLength() {
		return this.filename.length() + this.fileType.length();
	}
	
	public String images() {
		return this.filename + "." + this.fileType;
	}
	public boolean sameItem(Item that) {
		return that.sameImage(this);
	}
	public boolean sameImage(Image that) {
		if (this.filename == that.filename &&
				this.size == that.size &&
				this.fileType == that.fileType) {
			return true;
		}
		else {
			return false;
		}
	}
}

class Link extends AItem {
	WebPage page;
	String name;
	
	Link(WebPage page, String name) {
		this.page = page;
		this.name = name;
	}
	
	public int getsize() {
		return this.page.totalImageSize();
	}
	
	public int textLength() {
		return this.name.length() + this.page.textLength();
	}
	
	public String images() {
		return this.page.images();
	}
	public boolean sameItem(Item that) {
		return that.sameLink(this);
	}
	public boolean sameLink(Link that) {
		if (this.name == that.name && this.page.sameWebPage(that.page)) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
