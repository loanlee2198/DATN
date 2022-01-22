package edu.fa.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "student")
@Entity(name = "student")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Student {

	@Id
	private String maSV;
	private String tenSV;
	private String location;

}
