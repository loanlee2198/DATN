package edu.fa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.fa.model.Type;

public interface TypeRepository extends JpaRepository<Type, Integer> {
}
