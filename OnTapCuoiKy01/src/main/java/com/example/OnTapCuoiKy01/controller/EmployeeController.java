package com.example.OnTapCuoiKy01.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.OnTapCuoiKy01.entity.Employee;
import com.example.OnTapCuoiKy01.repository.EmployeeRepository;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeRepository dao;

	@GetMapping({ "show-employees", "/", "list" })
	public ModelAndView showEmployee() {
		ModelAndView mav = new ModelAndView("list-employees");
		List<Employee> listEmployees = dao.findAll();
		mav.addObject("employees", listEmployees);
		return mav;
	}

	@GetMapping("/add-employee-form")
	public ModelAndView addEmployee() {
		ModelAndView mav = new ModelAndView("add-employee-form");
		Employee e = new Employee();
		mav.addObject("employee", e);
		return mav;
	}

	@PostMapping("/create-employee")
	public String saveEmployee(@ModelAttribute Employee e) {
		dao.save(e);
		return "redirect:/list";
	}

	@GetMapping("/show-form-update")
	public ModelAndView showFormUpdate(@RequestParam int id) {
		ModelAndView mav = new ModelAndView("add-employee-form");
		Employee e = dao.findById(id).get();
		mav.addObject("employee", e);
		return mav;
	}
	
}
