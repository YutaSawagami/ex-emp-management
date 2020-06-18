package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository empRepository;
	
	public List<Employee> showList(){
		List<Employee> employeeList = empRepository.findAll();
		return employeeList;
	}
	
	public Employee showDetail(int id) {
		Employee emp = empRepository.load(id);
		return emp;
	}
	
	public void update(Employee employee) {
		empRepository.update(employee);
	}
}
