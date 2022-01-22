package edu.fa.model;

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

@Table(name = "customer")
@Entity(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customerid")
	private Integer id;

	@Column(name = "fullname")
	private String fullname;

	@Column(name = "phone")
	private String phone;

	@Column(name = "address")
	private String address;
	
	@Column(name = "job")
	private String job;

	@Column(name = "email")
	private String email;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;
	@Column(name = "role")
	private String role;

	public Customer(String fullname, String phone, String address, String job,String email, String username, String password,
			String role) {
		this.fullname = fullname;
		this.phone = phone;
		this.address = address;
		this.job=job;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
	}
}
