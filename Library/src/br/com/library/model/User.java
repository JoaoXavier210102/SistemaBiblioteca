package br.com.library.model;

public class User {
	
	private int id;
	private String name;
	private String type;
	private int password;
	private int borrowing;
	
	//Métodos GET e SET
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getBorrowing() {
		return borrowing;
	}
	public void setBorrowing(int borrowing) {
		this.borrowing = borrowing;
	}
	
}
