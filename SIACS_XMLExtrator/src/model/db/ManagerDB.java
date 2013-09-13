package model.db;

import model.business.Attribute;
import model.business.Element;
import model.business.Value;

public class ManagerDB {
	private static ManagerDB singleton = null;	
	
	private ElementDAOMySql elementDAOMySQL;
	private AttributeDAOMySql attributeDAOMySql;
	private ValueDAOMySql valueDAOMySql;

	public static ManagerDB getInstance() {
		if (singleton == null) {
			singleton = new ManagerDB();
		}
		return singleton;
	}
	
	private ManagerDB () {
		elementDAOMySQL = new ElementDAOMySql();
		attributeDAOMySql = new AttributeDAOMySql();
		valueDAOMySql = new ValueDAOMySql();
	}
	
	public void insertElement(Element element){
		try {
			elementDAOMySQL.save(element);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertAttribute(Attribute attribute){
		try {
			attributeDAOMySql.save(attribute);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertValue(Value value){
		try {
			valueDAOMySql.save(value);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
}
