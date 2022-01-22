package edu.fa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.fa.model.Student;

public interface StudentRepository extends JpaRepository<Student, String> {
//	Student findByNameAndLocation(String name, String location);
}
