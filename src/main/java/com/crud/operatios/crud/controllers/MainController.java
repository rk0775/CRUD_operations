package com.crud.operatios.crud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crud.operatios.crud.Entitys.Student;
import com.crud.operatios.crud.Repositorys.StudentRepository;

//to create rest api @RestController annotation is used
@RestController
public class MainController {
	@Autowired
	StudentRepository studentRepository;
	
	//save the student details
	@PostMapping("/saveStudent")
	public String saveStudentDetailsInDatabase(@RequestBody Student student) {
		try {
			studentRepository.save(student);
			return "student details is saved on database.";
		}catch(Exception e) {
			e.printStackTrace();
			return "Opps, something went wrong!!";
		}
	}
	
	//update the student details by roll number
	@PostMapping("/updateStudent/{rollNo}")
	public String updateStudentDetails(@PathVariable int rollNo,@RequestBody Student student) {
		Student std=studentRepository.findById(rollNo).get();
		
		//first check the values is not null and not empty and then set to the variables
		if(student.getName()!=null && !student.getName().equals("")) {
			std.setName(student.getName());
		}
		if(student.getEmail()!=null && !student.getEmail().equals("")) {
			std.setEmail(student.getEmail());
		}
		if(student.getContact()!=null && !student.getContact().equals("")) {
			std.setContact(student.getContact());
		}
		if(student.getAge()!=0) {
			std.setAge(student.getAge());
		}
		if(student.getAddress()!=null && !student.getAddress().equals("")) {
			std.setAddress(student.getAddress());
		}
		
		studentRepository.save(std);
		return "student information is successfully updated.";
		
	}
	
	//delete the student details
	@GetMapping("/deleteStudent/{rollNo}")
	public String deleteStudentDetailsFromDatabase(@PathVariable int rollNo) {
		try {
			studentRepository.delete(studentRepository.findById(rollNo).get());
			return "student details is removed from database";
		}catch(Exception e) {
			e.printStackTrace();
			return "Opps, something went wrong!!!";
		}
	}
	
	//simply get the all students from the database
	@GetMapping("/getStudents")
	public List<Student> getAllStudentsDetails() {
		List<Student> students=studentRepository.findAll();
		return students;
	}
	
}
