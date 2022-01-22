package edu.fa.dto;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.criteria.CriteriaBuilder.In;

import edu.fa.model.Book;
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
public class CustomerForgotPasswordDto {

	private String username;
	private String phone;

}
