package edu.fa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.fa.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, String> {

	public Admin findByUsername(String string);

	public Admin findByUsernameAndPassword(String username, String password);

//	public List<Order> findAllOrder();
}
