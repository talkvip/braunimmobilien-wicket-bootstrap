package org.apache.cocoon.sample.controller;
import java.util.List;
 import java.util.ArrayList;

public class Customer {
 
	String name;
	int age;
	int id;

 private List<String> strings = new ArrayList<String>();

	public String getName() {
		return name;
	}
 
	
	public void setName(String name) {
		this.name = name;
	}
 
	public int getAge() {
		return age;
	}
 
	
	public void setAge(int age) {
		this.age = age;
	}
 
	public int getId() {
		return id;
	}
 
	
	public void setId(int id) {
		this.id = id;
	}
 public List<String> getStrings() {
	return strings;
}


public void setStrings(List<String> strings) {
	this.strings = strings;
}

  public void addString(String param) {
        getStrings().add(param);
    }
}
