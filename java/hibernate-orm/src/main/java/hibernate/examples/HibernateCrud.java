package hibernate.examples;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import hibernate.entity.Company;
import hibernate.entity.Employee;
import hibernate.entity.Title;

public class HibernateCrud {

	private SessionFactory sessionFactory;

	public static void main(String[] args) {
		HibernateCrud h = new HibernateCrud();

		h.openHibernateSessionFactory();
		h.listEmployees();
		Employee employee = h.addEmployee("hibernate employee crud 1", 20, 2000.0, 1);
		Employee employee_2 = h.addEmployee("hibernate employee crud 2", 20, 2000.0, 1);
		System.out.println("my new employee id is: " + employee.getId());
		employee.setName(employee.getName() + "_v2");
		Employee updatedEmployee = h.updateEmployee(employee);
		System.out.println("the updated name of this employee is: " + updatedEmployee.getName());
		h.deleteEmployee(employee_2);
		h.listEmployees();
		h.closeHibernateSessionFactory();
	}

	private void deleteEmployee(Employee employee) {
		Session session = null;
		Transaction tx = null;

		try {
			session = getHibernateSession();
			tx = session.beginTransaction();
			session.delete(employee);
			System.out.println("deleted: " + employee);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			closeHibernateSession(session);
		}

	}

	private Employee updateEmployee(Employee employee) {
		Session session = null;
		Transaction tx = null;
		Employee updatedEmployee = null;

		try {
			session = getHibernateSession();
			tx = session.beginTransaction();
			session.update(employee);
			updatedEmployee = session.get(Employee.class, employee.getId());
			System.out.println("updated: " + updatedEmployee);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			closeHibernateSession(session);
		}

		return updatedEmployee;
	}

	private Employee addEmployee(String name, int age, double salary, int companyId) {
		Session session = null;
		Transaction tx = null;
		Employee newEmployee = null;

		try {
			session = getHibernateSession();
			tx = session.beginTransaction();

			Company company = session.get(Company.class, 1);

			Employee emp = new Employee(null, name, age, salary, company, null);
			Integer newEmployeeId = (Integer) session.save(emp);
			// Get the persisted object from the database
			newEmployee = session.get(Employee.class, newEmployeeId);

			Title title = new Title(null, "New Title", newEmployee);
			session.save(title);

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			closeHibernateSession(session);
		}

		return newEmployee;
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

	@SuppressWarnings("unchecked")
	private void listEmployees() {
		Session session = null;
		Transaction tx = null;

		try {
			session = getHibernateSession();
			tx = session.beginTransaction();
			List<Employee> employeeList = session.createQuery("From MyEmployeeEntity").getResultList();
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

}
