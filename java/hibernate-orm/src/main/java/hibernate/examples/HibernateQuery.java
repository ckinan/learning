package hibernate.examples;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import hibernate.entity.Employee;

public class HibernateQuery {

	private SessionFactory sessionFactory;

	public static void main(String[] args) {
		HibernateQuery h = new HibernateQuery();
		h.openHibernateSessionFactory();

		h.findEmployeeByName("hibernate employee crud");
		h.getSalaryAverage();
		h.increaseSalaryByEmployeeId(1, 502.8);
		h.countTitlesByEmployeeId(1);
		
		h.closeHibernateSessionFactory();
	}

	@SuppressWarnings("rawtypes")
	private void countTitlesByEmployeeId(int employeeId) {
		Session session = null;
		Transaction tx = null;

		try {
			session = getHibernateSession();
			tx = session.beginTransaction();
			Query query = session
					.createQuery("Select count(T1.id), T2.id, T2.name "
							+ "From MyTitleEntity T1 "
							+ "Inner Join T1.employee T2 "
							+ "Where T1.employee.id = :p_employee_id "
							+ "Group by T2.id, T2.name");
			query.setParameter("p_employee_id", employeeId);
			List titleList = query.getResultList();
			Iterator titleIterator = titleList.iterator();
			while (titleIterator.hasNext()) {
				System.out.println("Titles by Employee ID: " + Arrays.toString((Object[]) titleIterator.next()));
			}

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			closeHibernateSession(session);
		}
	}

	@SuppressWarnings("rawtypes")
	private void increaseSalaryByEmployeeId(int employeeId, double amount) {
		Session session = null;
		Transaction tx = null;

		try {
			// Retrieve the Employee
			session = getHibernateSession();
			tx = session.beginTransaction();

			// Calculate new salary
			Employee emp = session.get(Employee.class, employeeId);
			Double newSalary = emp.getSalary() + amount;

			Query query = session
					.createQuery("Update MyEmployeeEntity T1 Set T1.salary = :p_new_salary where T1.id = :p_id");

			query.setParameter("p_new_salary", newSalary);
			query.setParameter("p_id", employeeId);

			int rowsAffected = query.executeUpdate();

			System.out.println("Rows affected: " + rowsAffected);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			closeHibernateSession(session);
		}
	}

	@SuppressWarnings("rawtypes")
	private void getSalaryAverage() {
		Session session = null;
		Transaction tx = null;

		try {
			session = getHibernateSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("Select avg(T1.salary), 'Avg Result' From MyEmployeeEntity T1");
			Object[] avgSalary = (Object[]) query.getSingleResult();
			System.out.println("Salary average: " + Arrays.toString(avgSalary));
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			closeHibernateSession(session);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void findEmployeeByName(String name) {
		Session session = null;
		Transaction tx = null;

		try {
			session = getHibernateSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("From MyEmployeeEntity T1 " + "Where T1.name Like :p_name");
			query.setParameter("p_name", "%" + name + "%");
			List<Employee> employeeList = query.getResultList();
			for (Employee employee : employeeList) {
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
