package springdatajpa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import springdatajpa.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

	List<Employee> findByNameLike(String name);
	
}
