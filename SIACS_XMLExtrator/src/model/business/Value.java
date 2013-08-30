package model.business;

public class Value{
	private int id_value;
	private String content;
	private Attribute attribute;
	
	public Value(){}

	public int getId_value() {
		return id_value;
	}

	public void setId_value(int id_value) {
		this.id_value = id_value;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}
	
	
	
}
