package edu.fa.dto;

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
public class CustomerRegisterDto {
	private String fullname;
	private String phone;
	private String address;
	private String job;
	private String email;
	private String username;
	private String password;

}
