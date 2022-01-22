package edu.fa.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

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
@Entity(name="bookCartDto")
public class BookCartDto {
	@Id
	private Integer id;
	private String name;
	private String image;
	private Integer amount;
	private Integer price;

}
