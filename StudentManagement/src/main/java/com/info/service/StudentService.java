package com.info.service;

import java.util.List;

import com.info.entity.Student;

public interface StudentService {
	List<Student> getAllStudents();
	Student saveStudent(Student student);
	Student getStudentById(int id);
	Student updateStudent(Student student);
	void deleteById(int id);
}
