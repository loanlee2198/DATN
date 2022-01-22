package edu.fa.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.fa.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	@Query(value = "select o.orderid,o.borrowtime,c.fullname,sum(op.amount*op.price),o.status" + " from orders o \n"
			+ "join customer c \n" + "on o.customerid=c.customerid\n" + "JOIN order_book op"
			+ " on o.orderid=op.orderid\n" + "group by o.orderid,o.borrowtime,c.fullname,o.status order by o.borrowTime DESC", nativeQuery = true)
	public List<Object[]> findOrder();

	@Query(value = "select o.orderid,o.borrowtime,c.fullname,sum(op.amount*op.price),o.status" + " from orders o \n"
			+ "join customer c \n" + "on o.customerid=c.customerid\n" + "JOIN order_book op"
			+ " on o.orderid=op.orderid\n" + " where o.borrowtime" + " BETWEEN  :start and :end "
			+ "group by o.orderid,o.borrowtime,c.fullname,o.status order by o.borrowtime DESC", nativeQuery = true)
	public List<Object[]> findOrderBetweenTwoDate(Date start, Date end);

	@Query(value = "select o.orderid,o.borrowtime,c.customerid,p.name,op.amount,op.price,o.status from orders o join order_book op on o.orderid=op.orderid join book p on op.bookid=p.bookid join customer c on o.customerid=c.customerid where o.orderid=:orderid ", nativeQuery = true)
	public List<Object[]> findBook(String orderid);

	public Order findOrderById(Integer id);

	@Query(value = "select Top 1 *from orders where customerid=:id ORDER BY orderid DESC", nativeQuery = true)
	public Order findOrderByCustomerId(Integer id);

	@Query(value = "select concat(DATEPART(DAY, o.borrowtime),'-',DATEPART(MONTH, o.borrowtime) ,'-',DATEPART(YEAR, o.borrowtime)) as abc,SUM(o.orderid) as su,sum(op.amount*op.price) as bca\r\n"
			+ "from orders o\r\n"
			+ "JOIN order_book op \r\n"
			+ "on o.orderid=op.orderid \r\n"
			+ "join book p \r\n"
			+ "on op.bookid=p.bookid\r\n"
			+ "GROUP BY DATEPART(DAY, o.borrowtime), DATEPART(MONTH, o.borrowtime),DATEPART(YEAR, o.borrowtime)", nativeQuery = true)
	public List<Object[]> getRevenueByDay();

	@Query(value = "select concat(DATEPART(MONTH, o.borrowtime),'-',DATEPART(YEAR, o.borrowtime)) as aaa,sum(op.amount*op.price) as aaaa,sum(op.amount*op.price) as aaaaa"
			+ " from orders o"
			+ " JOIN order_book op"
			+ " on o.orderid=op.orderid"
			+ " join book p"
			+ " on op.bookid=p.bookid"
			+ " group by DATEPART(MONTH, o.borrowtime), DATEPART(YEAR, o.borrowtime)", nativeQuery = true)
	public List<Object[]> getRevenueByMonth();

	@Query(value = "select CONVERT(varchar(10), DATEPART(YEAR, o.borrowtime)) as aaa,sum(op.amount*op.price) as aaaa,sum(op.amount*op.price) as aaaaa"
			+ " from orders o"
			+ " JOIN order_book op"
			+ " on o.orderid=op.orderid"
			+ " join book p"
			+ " on op.bookid=p.bookid"
			+ " group by DATEPART(YEAR, o.borrowtime)", nativeQuery = true)
	public List<Object[]> getRevenueByYear();

}
