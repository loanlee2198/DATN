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

@Table(name = "status")
@Entity(name = "status")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Status {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "statusid")
	private Integer id;

	@Column(name = "name")
	private String name;
}
