package com.example.OnTapCuoiKy02.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.OnTapCuoiKy02.entity.Department;
import com.example.OnTapCuoiKy02.entity.Employee;
import com.example.OnTapCuoiKy02.repository.DepartmentRepository;
import com.example.OnTapCuoiKy02.repository.EmployeeRepository;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeRepository emRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@GetMapping("/list")
	public ModelAndView sayHello() {
		ModelAndView mav = new ModelAndView("hello");
		List<Employee> employees = emRepository.findAll();
		mav.addObject("employees", employees);
		return mav;
	}

	@GetMapping("/show-form-add")
	public ModelAndView showFormAdd() {
		ModelAndView mav = new ModelAndView("add-employee-form");
		List<Department> departments = departmentRepository.findAll();
		Employee e = new Employee();
		mav.addObject("employee", e);
		mav.addObject("departments", departments);
		return mav;
	}

	@PostMapping("/create-employee")
	public String createEmployee(@ModelAttribute Employee e) {
		emRepository.save(e);
		return "redirect:/list";
	}

	@GetMapping("/update-employee")
	public ModelAndView showFormUpdate(@RequestParam int id) {
		ModelAndView mav = new ModelAndView("add-employee-form");
		List<Department> departments = departmentRepository.findAll();
		Employee employee = emRepository.findById(id).get();
		mav.addObject("employee", employee);
		mav.addObject("departments", departments);
		return mav;
	}

	@PostMapping("/delete-employee")
	public String deleteEmployee(@RequestParam int id) {
		Employee e = emRepository.findById(id).get();
		emRepository.delete(e);
		return "redirect:/list";
	}

}
