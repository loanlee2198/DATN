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

@Table(name = "type")
@Entity(name = "type")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Type {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "typeid")
	private Integer id;
	@Column(name = "name")
	private String name;

	public Type(String name) {
		super();
		this.name = name;
	}

}
