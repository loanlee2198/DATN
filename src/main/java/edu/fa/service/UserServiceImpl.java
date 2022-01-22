package edu.fa.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fa.dto.CustomerForgotPasswordDto;
import edu.fa.dto.CustomerRegisterDto;
import edu.fa.model.Customer;
import edu.fa.model.Order;
import edu.fa.model.OrderBook;
import edu.fa.model.Book;
import edu.fa.model.Type;
import edu.fa.repository.CustomerRepository;
import edu.fa.repository.OrderBookRepository;
import edu.fa.repository.OrderRepository;
import edu.fa.repository.BookRepository;
import edu.fa.repository.TypeRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private TypeRepository typeRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	OrderBookRepository orderBookRepository;

	// @Autowired
	private CustomerRepository customerRepository;

	public UserServiceImpl(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	@Override
	public Customer save(CustomerRegisterDto registerDto) {
		Customer customer = new Customer(registerDto.getFullname(), registerDto.getPhone(), registerDto.getAddress(),
				registerDto.getJob(), registerDto.getEmail(), registerDto.getUsername(), registerDto.getPassword(),
				"ROLE_USER");

		return customerRepository.save(customer);
	}

	@Override
	public Customer findByUsernameAndPassword(String username, String password) {

		Customer customer = customerRepository.findByUsernameAndPassword(username, password);
		if (customer == null) {
			return null;
		} else {
			return customer;
		}

	}

	@Override
	public Set<Book> search(String keyword) {
		return bookRepository.search(keyword);
	}

	@Override
	public Book findBookById(Integer id) {
		return bookRepository.findBookById(id);
	}

	@Override
	public List<Type> findAll() {
		return typeRepository.findAll();
	}

	@Override
	public List<Book> findAllBook(Boolean b) {
		return bookRepository.findAllByIsDelete(b);
	}

	@Override
	public void addOrder(Order order) {
		orderRepository.save(order);
	}

	@Override
	public void save(OrderBook op) {
		orderBookRepository.save(op);
	}

	@Override
	public Order findOrderByCustomerId(Integer id) {
		return orderRepository.findOrderByCustomerId(id);
	}

	@Override
	public void update(Customer customer) {
		customerRepository.save(customer);

	}

	@Override
	public Customer findCustomerByUsernameAndPhone(CustomerForgotPasswordDto customerPass) {
		System.out.println(customerPass.toString());
		return customerRepository.findByPhoneAndUsername(customerPass.getPhone(), customerPass.getUsername());
	}

	@Override
	public List<Object[]> findOrderByYour(Integer id) {
		return orderBookRepository.findOrderByYour(id);
	}


}
