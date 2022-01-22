package edu.fa.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "orders")
@Entity(name = "order")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "orderid")
	private Integer id;
	@Column(name = "customerid")
	private int customerid;
	@Column(name = "borrowtime")
	private Date borrowTime ;
	@Column(name = "returntime")
	private Date returnTime ;
	@Column(name = "status")
	private Boolean status;

	public Order(int customerid, Date borrowTime, Boolean status) {
		this.customerid = customerid;
		this.borrowTime = borrowTime;
		this.status = status;
	}

}
