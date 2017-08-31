package bean;

public class Book {
	
	private Integer id;
	private String name;
	private String author;
	private double price;
	private String description;
	
	public Book(){
		super();
	}
	
	public Book(Integer id, String name, String author, double price, String description){
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.price = price;
		this.description = description;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString(){
		return "Book[" + this.getId() + "]: " + this.getName() + ", written by " + this.getAuthor()
		+ ", price: " + this.getPrice() + ", desc: " + this.getDescription();
	}

}
