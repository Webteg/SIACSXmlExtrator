package model.business;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import model.util.Hibernate;

public class HibernateTest {
	public static void main(String[] args){
		HibernateTest main = new HibernateTest();
		main.insertElement();
		//main.insertValue();
		//main.getOneTag();
	}

	public void insertElement(){
		Session session = Hibernate.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			Element element = new Element();
			element.setName("CURRICULO-VITAE");
			
			Element element2 = new Element();
			element2.setName("DADOS-GERAIS");
			element2.setParent_element(element);

			session.save(element);
			session.save(element2);
			transaction.commit();
			System.out.println("Records inserted sucessessfully");     
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}		

	}
	/*
	public void insertValue(){
		Session session = Hibernate.getSessionFactory().openSession();
		Transaction transaction = null;

		try{
			transaction = session.beginTransaction();

			Value value = new Value();
			value.setContent("hi");
			Tag tag_ = (Tag) session.createQuery("from Tag where children_tag like '%TESTE%' ").uniqueResult();
			value.setTag(tag_);	

			session.save(value);
			transaction.commit();
			System.out.println("Records inserted sucessessfully");     
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}	
	}

	public void getOneTag() {
		Session session = Hibernate.getSessionFactory().openSession();
		Tag tag_ = (Tag) session.createQuery("from Tag where children_tag like '%TESTE%' ").uniqueResult();
		System.out.println(tag_.getId_tag());
	}
	*/

}
