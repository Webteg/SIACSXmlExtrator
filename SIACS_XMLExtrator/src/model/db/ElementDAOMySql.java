package model.db;

import model.business.Element;

public class ElementDAOMySql implements IElementDAO{

	@Override
	public void save(Element element) throws Exception {
		String sql = new String(); 
		sql = "INSERT INTO element (name, id_parent_element) VALUES ('" + element.getName() +"', '"+element.getId_parent_element()+"')";

	}

}
