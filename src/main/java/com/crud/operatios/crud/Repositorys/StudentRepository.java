package com.crud.operatios.crud.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.operatios.crud.Entitys.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
