package model.db;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.business.Element;
import model.util.Hibernate;

public class ElementDAOHibernate implements IElementDAO {
	private Session session = Hibernate.getSessionFactory().openSession();

	@Override
	public void save(Element element) throws Exception {
		Transaction t = session.beginTransaction();
		session.save(element);
		t.commit();		
	}

}
