package edu.fa.dto;

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
public class OrderBookDto {

	private Book book;
	private Integer quantity;

}
