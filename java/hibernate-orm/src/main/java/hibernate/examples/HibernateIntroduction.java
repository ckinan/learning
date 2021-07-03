package hibernate.examples;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.entity.Employee;

@SuppressWarnings("deprecation")
public class HibernateIntroduction {

	@SuppressWarnings({ "unchecked" })
	public static void main(String[] args) {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		// Configuration configuration = new Configuration().configure("hibername.properties.bkp");
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();

		session.beginTransaction();
		Employee emp1 = new Employee(null, "hibernate_employee", 20, 2000.0, null, null);
		session.save(emp1);
		session.getTransaction().commit();
		List<Employee> resultList = session.createQuery("From MyEmployeeEntity ").getResultList();
		System.out.println("number of employees:" + resultList.size());
		for (Employee emp : resultList) {
			System.out.println("employee: " + emp);
		}
		session.close(); // Close the physical connection with the DB
		sessionFactory.close();
	}

}
