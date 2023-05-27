package com.example.OnTapCuoiKy03.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.OnTapCuoiKy03.entity.Department;
import com.example.OnTapCuoiKy03.entity.Employee;
import com.example.OnTapCuoiKy03.repository.DepartmentRepository;
import com.example.OnTapCuoiKy03.repository.EmployeeRepository;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@GetMapping("/list")
	public ModelAndView getList() {
		ModelAndView mav = new ModelAndView("list-employees");
		List<Employee> employees = employeeRepository.findAll();
		mav.addObject("employees", employees);
		return mav;
	}

	@GetMapping("/show-form-employee")
	public ModelAndView showForm() {
		ModelAndView mav = new ModelAndView("form-employee");
		List<com.example.OnTapCuoiKy03.entity.Department> departments = departmentRepository.findAll();
		Employee e = new Employee();
		mav.addObject("employee", e);
		mav.addObject("departments", departments);
		return mav;
	}

	@PostMapping("/create")
	public String createEmployee(@ModelAttribute Employee e) {
		employeeRepository.save(e);
		return "redirect:/list";
	}

	@GetMapping("/update-form-employee")
	public ModelAndView showFormUpdate(@RequestParam int id) {
		ModelAndView mav = new ModelAndView("form-employee");
		List<Department> departments = departmentRepository.findAll();
		Employee employee = employeeRepository.findById(id).get();
		mav.addObject("employee", employee);
		mav.addObject("departments", departments);
		return mav;
	}

	@PostMapping("/delete")
	public String deleteEmployee(@RequestParam int id) {
		Employee e = employeeRepository.findById(id).get();
		employeeRepository.delete(e);
		return "redirect:/list";
	}

	@GetMapping("/delete-employee")
	public String deleteEmployees(@RequestParam int id) {
		Employee e = employeeRepository.findById(id).get();
		employeeRepository.delete(e);
		return "redirect:/list";
	}
}
