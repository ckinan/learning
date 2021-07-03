package hibernate.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="MyEmployeeEntity") // Name of the Entity read by Hibernate JPQL
@Table(name="employee") // Table name existing in the Database
public class Employee {

	@Id
	// AUTO (hibernate_sequence), IDENTITY (DB manages the ID AI)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name="name")
	private String name;
	@Column(name="age")
	private Integer age;
	@Column(name="salary")
	private Double salary;
	@ManyToOne
	@JoinColumn(name="company_id")
	private Company company;
	@OneToMany(mappedBy="employee")
	private List<Title> titles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", age=" + age + ", salary=" + salary + ", company=" + company
				+ ", titles=" + titles + "]";
	}

	public Employee(){}

	public Employee(Integer id, String name, Integer age, Double salary, Company company, List<Title> titles) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.company = company;
		this.titles = titles;
	}
	
}
