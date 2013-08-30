package model.db;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.business.Value;
import model.util.Hibernate;

public class ValueDAOHibernate implements IValueDAO {
	private Session session = Hibernate.getSessionFactory().openSession();
	
	@Override
	public void save(Value value) throws Exception {
		Transaction t = session.beginTransaction();
        session.save(value);
        t.commit();		
	}

}
