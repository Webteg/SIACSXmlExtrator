package model.db;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.business.Attribute;
import model.util.Hibernate;

public class AttributeDAOHibernate implements IAttributeDAO {
	private Session session = Hibernate.getSessionFactory().openSession();

	@Override
	public void save(Attribute attribute) throws Exception {
		Transaction t = session.beginTransaction();
        session.save(attribute);
        t.commit();		
	}

}
