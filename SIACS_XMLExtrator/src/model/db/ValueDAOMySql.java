package model.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.business.Attribute;
import model.business.Value;

public class ValueDAOMySql implements IValueDAO{
	private BDMySql bdMysql = BDMySql.getInstance();

	@Override
	public void save(Value value) throws Exception {
		String sql = new String(); 
		int id_attribute = this.getIdAttribute(value.getAttribute());
		sql = "INSERT INTO value (content, id_attribute) VALUES ('" + value.getContent() +"', '"+id_attribute+"')";
		bdMysql.executarSQL(sql);
	}

	public int getIdAttribute(Attribute attribute){
		int id_attribute = 1;
		String sql = new String();
		sql = "select id_attribute from attribute where name = '" +attribute.getName()+"'";
		ResultSet rs = bdMysql.executarBuscaSQL(sql);
		if (rs == null) {
			// TODO
			id_attribute = 1;
		} else
			try {
				if(rs.next()) {
					id_attribute = rs.getInt("id_attribute");
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		return id_attribute;
	}

	
}
