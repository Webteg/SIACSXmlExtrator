package model.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.business.Attribute;
import model.business.Element;

public class AttributeDAOMySql implements IAttributeDAO{
	private BDMySql bdMysql = BDMySql.getInstance();

	@Override
	public void save(Attribute attribute) throws Exception {
		String sql = new String(); 
		int id_element = this.getIdElement(attribute.getElement());
		sql = "INSERT INTO attribute (name, id_element) VALUES ('" + attribute.getName() +"', '"+id_element+"')";
		bdMysql.executarSQL(sql);
	}

	public int getIdElement(Element element){
		int id_element = 1;
		String sql = new String();
		sql = "select id_element from element where name = '" +element.getName()+"'";
		ResultSet rs = bdMysql.executarBuscaSQL(sql);
		if (rs == null) {
			// TODO
			id_element = 1;
		} else
			try {
				if(rs.next()) {
					id_element = rs.getInt("id_element");
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		return id_element;
	}
	
	
}
