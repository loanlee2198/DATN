package edu.fa.service;

import java.util.List;
import java.util.Set;

import edu.fa.dto.CustomerForgotPasswordDto;
import edu.fa.dto.CustomerRegisterDto;
import edu.fa.model.Customer;
import edu.fa.model.Order;
import edu.fa.model.OrderBook;
import edu.fa.model.Book;
import edu.fa.model.Type;

public interface UserService {
	Customer save(CustomerRegisterDto registerDto);
	Customer findByUsernameAndPassword(String username,String password);
	Book findBookById(Integer id);
	List<Type> findAll();
	List<Book> findAllBook(Boolean b);
	public Set<Book> search(String keyword);
	void addOrder(Order order);
	void save(OrderBook op);
	Order findOrderByCustomerId(Integer id);
	void update(Customer customer);
	Customer findCustomerByUsernameAndPhone(CustomerForgotPasswordDto customerPass);
	List<Object[]> findOrderByYour(Integer id);
}
