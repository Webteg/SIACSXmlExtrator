package model.business;

public class Value{
	private int id_value;
	private String content;
	private Tag tag;
	
	public Value(){}
	
	public void setId_value(int id_value_) {
		this.id_value = id_value_;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public void setTag(Tag tag_) {
		this.tag = tag_;
	}

	public int getId_value() {
		return id_value;
	}
	
	public String getContent() {
		return content;
	}
	
	public Tag getTag() {
		return tag;
	}
	
}
