package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;
	
	@ModelAttribute
	public UpdateEmployeeForm setUpUpdateEmployeeForm() {
		return new UpdateEmployeeForm();
	}
	
	@RequestMapping("/showList")
	public String showList(Model model) {
	List<Employee> empList = empService.showList();
	for(Employee emp: empList) {
		System.out.print(emp.getGender());
	}
	model.addAttribute("employeeList", empList);
	return "employee/list";
	}
	
	@RequestMapping("/showDetail")
	public String index(String id, Model model) {
		
		Employee emp = empService.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", emp);
		return "employee/detail";
	}
	
	@RequestMapping("/update")
	public String update(UpdateEmployeeForm updateEmployeeForm) {
		Employee employee = new Employee();
		System.out.println(updateEmployeeForm.getId());
		employee.setDependentsCount(updateEmployeeForm.getDependentsCount());
		empService.update(employee);
		return "redirect:/employee/showList";
	}
}
