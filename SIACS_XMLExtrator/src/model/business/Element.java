package model.business;

import java.util.HashSet;
import java.util.Set;

public class Element {
	 private int id_element;
	 private String name;
	 private Set<Attribute> attribute = new HashSet<Attribute>(0);
	 
	 public Element(){}

	public int getId_element() {
		return id_element;
	}

	public void setId_element(int id_element) {
		this.id_element = id_element;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Attribute> getAttribute() {
		return attribute;
	}

	public void setAttribute(Set<Attribute> attribute) {
		this.attribute = attribute;
	}
	 
	 
	 
	 
}
