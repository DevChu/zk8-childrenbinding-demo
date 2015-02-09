package org.zkoss.blog.example.childrenbinding;

public class Product {
	private Integer id;
	private String name;
	private Integer price;
	
	public Product() {
	}
	
	public Product(Integer i, String n, Integer p) {
		this.id = i;
		this.name = n;
		this.price = p;
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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "[id = " + id + ", name = " + name + ", price = " + price + "]";
	}
	
}
