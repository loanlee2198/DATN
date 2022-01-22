package edu.fa.service;

import java.sql.Date;
import java.util.List;

import edu.fa.dto.NewBookDto;
import edu.fa.model.Admin;
import edu.fa.model.Customer;
import edu.fa.model.Order;
import edu.fa.model.OrderBook;
import edu.fa.model.Type;
import edu.fa.model.Book;

public interface AdminService {

	public Admin findByUsernameAndPassword(String username, String password);

	public Book createNewBook(NewBookDto newBookDto);

	public List<Book> findAllBook(Boolean b);

	public void delete(Integer id);

	public Book findBookById(Integer id);

	public Book findOne(Integer id);

	public void save(Book book);

	public List<Object[]> findAllOrder();
	public List<Object[]> findBookOfOrder(String id);

	public Customer findCustomerById(Integer customerId);

	public List<Object[]> findOrderBetweenTwoDate(Date dayStart, Date dayend);

	public List<Customer> findAllCustomer();

	public Order findOrderById(Integer id);

	public void saveOrder(Order order);

	public void deleteOrder(Order order);

	public void deleteOrderBook(OrderBook orderBook);

	public List<OrderBook> findAllOrderBookById(Integer id);

	public List<Object[]> getRevenueDay();

	public List<Object[]> getRevenueMonth();

	public List<Object[]> getRevenueYear();

	public void createNewTypeBook(Type newBookDto);
}
