
public class WebPage {
	String url;
	String title;
	ILoItem item;
	
	public WebPage(String url, String title, ILoItem item) {
		url = this.url;
		title = this.title;
		item = this.item;
	}
	
	public int totalImageSize() {
		return item.getsize();
	}
	
	public int textLength() {
		return title.length() + item.textLength();
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
		first = this.first;
		rest = this.rest;
	}
	
	public int getsize() {
		return first.getsize() + rest.getsize();
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
		contents = this.contents;
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
		filename = this.filename;
		size = this.size;
		fileType = this.fileType;
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
		page = this.page;
		name = this.name;
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
