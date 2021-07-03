package springdatajpa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springdatajpa.entity.Employee;
import springdatajpa.repository.EmployeeRepository;
import springdatajpa.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Employee> findByNameLike(String name) {
		return employeeRepository.findByNameLike(name);
	}

}
