package model.business;

import java.util.HashSet;
import java.util.Set;

public class Element {
	 private int id_element;
	 private String root_name;
	 private String children_name;
	 private Set<Attribute> attribute = new HashSet<Attribute>(0);
	 
	 public Element(){}

	public int getId_element() {
		return id_element;
	}

	public void setId_element(int id_element) {
		this.id_element = id_element;
	}

	public String getRoot_name() {
		return root_name;
	}

	public void setRoot_name(String root_name) {
		this.root_name = root_name;
	}

	public String getChildren_name() {
		return children_name;
	}

	public void setChildren_name(String children_name) {
		this.children_name = children_name;
	}

	public Set<Attribute> getAttribute() {
		return attribute;
	}

	public void setAttribute(Set<Attribute> attribute) {
		this.attribute = attribute;
	}
	 
	@Override
	public String toString() {
		return "Root element : " + this.getRoot_name() + " Children element : " + this.getChildren_name();
	}
	 
	 
}
