package springdatajpa.controller;

import org.springframework.web.bind.annotation.RestController;

import springdatajpa.entity.Employee;
import springdatajpa.service.EmployeeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class MyController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/")
	public String index() {
		return "Explore: /countEmployees";
	}

	@RequestMapping("/countEmployees")
	public String countEmployees(@RequestParam(value = "name", defaultValue = "") String name) {
		List<Employee> result = employeeService.findByNameLike("%" + name + "%");
		return "Number of employees: " + result.size();
	}

}