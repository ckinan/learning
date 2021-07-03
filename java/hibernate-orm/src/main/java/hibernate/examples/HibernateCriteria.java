package hibernate.examples;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import hibernate.entity.Employee;

public class HibernateCriteria {

	private SessionFactory sessionFactory;

	public static void main(String[] args) {
		HibernateCriteria h = new HibernateCriteria();
		h.openHibernateSessionFactory();

		h.findEmployeeByGreaterSalary(2500.0);

		h.closeHibernateSessionFactory();
	}

	private void findEmployeeByGreaterSalary(double salary) {
		Session session = null;
		Transaction tx = null;

		try {
			session = getHibernateSession();
			tx = session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
			Root<Employee> root = criteria.from(Employee.class);
			criteria.select(root);
			criteria.where(builder.greaterThanOrEqualTo(root.get("salary"), salary));
			List<Employee> result = session.createQuery(criteria).getResultList();
			
			for (Employee employee : result) {
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
