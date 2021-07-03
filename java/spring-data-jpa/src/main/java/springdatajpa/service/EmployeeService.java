package springdatajpa.service;

import java.util.List;

import springdatajpa.entity.Employee;

public interface EmployeeService {

	public List<Employee> findByNameLike(String name);
	
}
