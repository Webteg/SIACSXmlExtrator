package model.business;

import java.util.HashSet;
import java.util.Set;

public class Element {
	private int id_element;
	private String name;
	private Element parent_element;
	private int id_parent_element;
    private Set<Element> element =  new HashSet<Element>(0);;
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

	public Element getParent_element() {
		return parent_element;
	}

	public void setParent_element(Element parent_element) {
		this.parent_element = parent_element;
	}

	public int getId_parent_element() {
		return id_parent_element;
	}

	public void setId_parent_element(int id_parent_element) {
		this.id_parent_element = id_parent_element;
	}

	public Set<Element> getElement() {
		return element;
	}

	public void setElement(Set<Element> element) {
		this.element = element;
	}

	public Set<Attribute> getAttribute() {
		return attribute;
	}

	public void setAttribute(Set<Attribute> attribute) {
		this.attribute = attribute;
	}
	
	public String toString(){
		return "Nome do elemento: " + this.getName() +
				" Elemento pai: " + this.getParent_element().getName();
	}
	 
}
