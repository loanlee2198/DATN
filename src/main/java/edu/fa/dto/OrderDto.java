package edu.fa.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class OrderDto {
//
//	public static final String ID = "id";
//	public static final String DATE = "date";
//	public static final String FULLNAME = "fullname";
//	public static final String TOTAL = "total";

	private Integer id;
	private Date date;
	private String fullname;
	private String status;
	private Integer total;
}
