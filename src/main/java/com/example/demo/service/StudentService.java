package com.example.demo.service;


import com.example.demo.model.Student;

public interface StudentService {
	int add(Student student);
    int update(Student student);
    int deleteBysno(String sno);
    Student queryStudentBySno(String sno);
}
