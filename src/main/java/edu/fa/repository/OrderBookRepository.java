package edu.fa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.fa.model.OrderBook;

public interface OrderBookRepository extends JpaRepository<OrderBook, Integer> {
	@Query(value = "select * from order_book where orderid=:id", nativeQuery = true)
	List<OrderBook> findAllById(Integer id);

	@Query(value = "select o.orderid,o.borrowtime,b.name,sum(ob.amount*ob.price)  from orders o \r\n"
			+ "			join customer c \r\n"
			+ "			on o.customerid=c.customerid\r\n"
			+ "			JOIN order_book ob\r\n"
			+ "			 on o.orderid=ob.orderid\r\n"
			+ "			 join book b\r\n"
			+ "			 on b.bookid=ob.bookid\r\n"
			+ "			  where c.customerid=3\r\n"
			+ "			 group by o.orderid,o.borrowtime,b.name order by o.borrowTime DESC", nativeQuery = true)
	public List<Object[]> findOrderByYour(Integer id);
}
