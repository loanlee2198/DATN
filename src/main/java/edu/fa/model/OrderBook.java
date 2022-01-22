package edu.fa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Table(name = "order_book")
@Entity(name = "order_book")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@IdClass(OrderBook.class)
public class OrderBook implements Serializable {
	@Id
	@Column(name = "orderid")
	private Integer orderId;

	@Id
	@Column(name = "bookid")
	private Integer bookId;

	@Column(name = "amount")
	private Integer amount;

	@Column(name = "price")
	private Integer price;

}
