package com.info.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.info.entity.Student;
import com.info.service.StudentService;


@Controller
@RequestMapping("/")
public class StudentController {
	
	@Autowired
	private StudentService studentService;


	@GetMapping("/map")
	public String getAll(Model model) {
		model.addAttribute("students",studentService.getAllStudents());
		return "view";
	}
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		
//		this object is introduce in order to hold student data
		Student student=new Student();
		model.addAttribute("student", student);
		return "Create_student";
	}
	@PostMapping("/students")
	public String saveStudents(@ModelAttribute("student") Student student) {
		studentService.saveStudent(student);
		return "redirect:/map";
	}
	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable int id,Model model) {
		model.addAttribute("student", studentService.getStudentById(id));
		return "edit_student";
	}
	
	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable int id,@ModelAttribute Student student,Model model) {
		
//		get Student From database
		Student exicistingStudent =studentService.getStudentById(id);
		exicistingStudent.setId(student.getId());
		exicistingStudent.setName(student.getName());
		exicistingStudent.setEmail(student.getEmail());
		exicistingStudent.setSchoolName(student.getSchoolName());
//		save updated student information
		studentService.updateStudent(exicistingStudent);
		return "redirect:/map";
		
	}
	
	@GetMapping("/students/{id}")
	
	public String deleteStudent(@PathVariable int id,Model model) {
		
		studentService.deleteById(id);
		return "redirect:/map";
	}
	}


