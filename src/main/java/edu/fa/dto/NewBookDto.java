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
public class NewBookDto {

	private String name;
	private String des;
	private String author;
	private int price;
	private String image;
	private int amount;
	private float promotion;
	private int type;
}
