package hibernate.examples;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

import hibernate.entity.Employee;

public class HibernateNativeSql {

	private SessionFactory sessionFactory;
	
	public static void main(String[] args) {
		HibernateNativeSql h = new HibernateNativeSql();
		h.openHibernateSessionFactory();
		
		h.findEmployeeByNameAndSalaryGreaterOrEqual("batch", 1500.0);
		
		h.closeHibernateSessionFactory();
	}
	
	@SuppressWarnings({ "rawtypes", "deprecation" })
	private void findEmployeeByNameAndSalaryGreaterOrEqual(String name, double salary) {
		Session session = null;
		Transaction tx = null;

		try {
			session = getHibernateSession();
			tx = session.beginTransaction();
			NativeQuery query = session.createNativeQuery("select * from employee where name like :p_name and salary >= :p_salary");
			query.addEntity(Employee.class);
			query.setParameter("p_name", "%" + name + "%");
			query.setParameter("p_salary", salary);
			List result = query.getResultList();
			for (Object employee : result) {
				System.out.println(employee);
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			closeHibernateSession(session);
		}
	}

	private void openHibernateSessionFactory() {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		sessionFactory = configuration.buildSessionFactory();
	}

	private Session getHibernateSession() {
		return sessionFactory.openSession();
	}

	private void closeHibernateSessionFactory() {
		if (sessionFactory != null && sessionFactory.isOpen()) {
			sessionFactory.close();
		}
	}

	private void closeHibernateSession(Session session) {
		if (session != null && session.isOpen()) {
			session.close();
		}
	}

}
