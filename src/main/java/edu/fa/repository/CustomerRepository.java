package edu.fa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.fa.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	public Customer findByUsernameAndPassword(String username, String password);

	public Customer findCustomerById(Integer customerId);
	public Customer findByPhoneAndUsername(String string,String username);

}
