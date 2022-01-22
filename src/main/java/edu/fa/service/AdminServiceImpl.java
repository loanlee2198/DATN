package edu.fa.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fa.dto.NewBookDto;
import edu.fa.model.Admin;
import edu.fa.model.Book;
import edu.fa.model.Customer;
import edu.fa.model.Order;
import edu.fa.model.OrderBook;
import edu.fa.model.Type;
import edu.fa.repository.AdminRepository;
import edu.fa.repository.BookRepository;
import edu.fa.repository.CustomerRepository;
import edu.fa.repository.OrderBookRepository;
import edu.fa.repository.OrderRepository;
import edu.fa.repository.TypeRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private TypeRepository typeRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	OrderBookRepository orderBookRepository;

	@Override
	public Admin findByUsernameAndPassword(String username, String password) {
		return adminRepository.findByUsernameAndPassword(username, password);
	}
	@Override
	public void createNewTypeBook(Type newBookDto) {
		typeRepository.save(newBookDto);
	}

	@Override
	public Book createNewBook(NewBookDto newBookDto) {
		Book book = new Book(newBookDto.getName(), newBookDto.getDes(), newBookDto.getAuthor(),
				newBookDto.getPrice(), newBookDto.getImage(), newBookDto.getAmount(),
				newBookDto.getPromotion(), newBookDto.getType());

		return bookRepository.save(book);
	}

	@Override
	public List<Object[]> findAllOrder() {

		return orderRepository.findOrder();
	}

	@Override
	public List<Book> findAllBook(Boolean b) {
		return bookRepository.findAllByIsDelete(b);
	}

	@Override
	public void delete(Integer id) {

		bookRepository.deleteById(id);

	}

	@Override
	public Book findBookById(Integer id) {
		return bookRepository.findBookById(id);
	}

	@Override
	public Book findOne(Integer id) {
		return bookRepository.findBookById(id);
	}

	@Override
	public void save(Book book) {
		bookRepository.save(book);
	}

	@Override
	public List<Object[]> findBookOfOrder(String id) {
		// TODO Auto-generated method stub
		return orderRepository.findBook(id);
	}

	@Override
	public Customer findCustomerById(Integer customerId) {
		return customerRepository.findCustomerById(customerId);

	}

	@Override
	public List<Object[]> findOrderBetweenTwoDate(Date dayStart, Date dayend) {
		return orderRepository.findOrderBetweenTwoDate(dayStart, dayend);
	}

	@Override
	public List<Customer> findAllCustomer() {
		return customerRepository.findAll();
	}

	@Override
	public Order findOrderById(Integer id) {

		return orderRepository.findOrderById(id);
	}

	@Override
	public void saveOrder(Order order) {
		orderRepository.save(order);
	}

	@Override
	public void deleteOrder(Order order) {
		orderRepository.delete(order);
	}

	@Override
	public void deleteOrderBook(OrderBook id) {
		orderBookRepository.delete(id);
	}

	@Override
	public List<OrderBook> findAllOrderBookById(Integer id) {
		return orderBookRepository.findAllById(id);
	}

	@Override
	public List<Object[]> getRevenueDay() {
		return orderRepository.getRevenueByDay();
	}

	@Override
	public List<Object[]> getRevenueMonth() {

		return orderRepository.getRevenueByMonth();
	}

	@Override
	public List<Object[]> getRevenueYear() {

		return orderRepository.getRevenueByYear();
	}

	

}
