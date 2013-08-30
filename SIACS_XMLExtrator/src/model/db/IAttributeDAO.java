package model.db;

import model.business.Attribute;

public interface IAttributeDAO {
	public void save(Attribute attribute) throws Exception;
}
