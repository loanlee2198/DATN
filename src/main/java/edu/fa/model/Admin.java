package edu.fa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "admin")
@Entity(name = "admin")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Admin {

	@Id
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
}
