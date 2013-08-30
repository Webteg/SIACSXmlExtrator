package model.db;

import model.business.Value;

public interface IValueDAO {
	public void save(Value value) throws Exception;
}
