package datatype;

public class Info {
	
	private String title;
	private String author;
	private int id;
	
	
	public Info(String title, String author, int id){
		initInfo(title, author, id);

	}
	
	private void initInfo(String title, String author, int id) {
		this.title = title;
		this.author = author;
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public int getId() {
		return id;
	}
}
