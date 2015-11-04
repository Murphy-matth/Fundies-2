
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
}


interface ILoItem {
	int getsize();
	int textLength();
	String images();
	boolean isCons();
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
}

interface Item {
	int getsize();
	int textLength();
	String images();
}

class Text implements Item {
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
}

class Image implements Item {
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
}

class Link implements Item {
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
	
}
