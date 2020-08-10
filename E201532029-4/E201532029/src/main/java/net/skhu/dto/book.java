package net.skhu.dto;

import java.util.List;

public class book {

	int id;
	String title;
	String author;
	int price;
	int available;
	List<category> categorys;
	List<publisher> publishers;
	String categoryName;
	String publisherName;
	int categoryId;
	int publisherId;
	private category category;
	private publisher publisher;



	public category getCategory() {
		return category;
	}
	public void setCategory(category category) {
		this.category = category;
	}
	public publisher getPublisher() {
		return publisher;
	}
	public void setPublisher(publisher publisher) {
		this.publisher = publisher;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	public List<category> getCategorys() {
		return categorys;
	}
	public void setCategorys(List<category> categorys) {
		this.categorys = categorys;
	}
	public List<publisher> getPublishers() {
		return publishers;
	}
	public void setPublishers(List<publisher> publishers) {
		this.publishers = publishers;
	}


}
