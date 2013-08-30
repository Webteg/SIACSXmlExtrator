package model.business;

import java.util.HashSet;
import java.util.Set;

public class Attribute {
	private int id_attribute;
	private String name;
	private Element element;
	private Set<Value> value = new HashSet<Value>(0);
	
	public Attribute(){}

	public int getId_attribute() {
		return id_attribute;
	}

	public void setId_attribute(int id_attribute) {
		this.id_attribute = id_attribute;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public Set<Value> getValue() {
		return value;
	}

	public void setValue(Set<Value> value) {
		this.value = value;
	} 
	
	
	
}
