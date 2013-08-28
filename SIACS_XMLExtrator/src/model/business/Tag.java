package model.business;

import java.util.HashSet;
import java.util.Set;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("tag")
public class Tag{
	private int id_tag;
	private String root_tag;
	private String children_tag;
	private Set<Value> value = new HashSet<Value>(0);
	
	public Tag(){}
	
	public void setId_tag(int id_tag_) {
		this.id_tag = id_tag_;
	}

	public void setRoot_tag(String root_tag_) {
		this.root_tag = root_tag_;
	}
	
	public void setChildren_tag(String children_tag_) {
		this.children_tag = children_tag_;
	}

	public void setValue(Set<Value> value_) {
		this.value = value_;
	}
	
	public int getId_tag() {
		return id_tag;
	}
	
	public String getRoot_tag() {
		return root_tag;
	}
	
	public String getChildren_tag() {
		return children_tag;
	}

	public Set<Value> getValue() {
		return value;
	}



}