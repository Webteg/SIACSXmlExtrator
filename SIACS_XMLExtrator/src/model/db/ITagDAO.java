package model.db;

import model.business.Tag;

public interface ITagDAO {
	
	public void saveTag(Tag tag_);
	public Tag getOneTag(String query);
	
}
