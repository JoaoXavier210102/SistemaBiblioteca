package br.com.library.model;

public class Book {
	
	private String authors;
	private String edition;
	private String publishingCompany;
	private String title;
	private int year;
	private int id;
	private long ISBN;
	private int borrowed;
	
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public long getISBN() {
		return ISBN;
	}
	public void setISBN(long ISBN) {
		this.ISBN = ISBN;
	}
	public String getPublishingCompany() {
		return publishingCompany;
	}
	public void setPublishingCompany(String publishingCompany) {
		this.publishingCompany = publishingCompany;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBorrowed() {
		return borrowed;
	}
	public void setBorrowed(int borrowed) {
		this.borrowed = borrowed;
	}
}
