package model.db;

import model.business.Element;

public interface IElementDAO {
	public void save(Element element) throws Exception;
}
