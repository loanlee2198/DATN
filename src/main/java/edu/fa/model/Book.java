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

@Table(name = "book")
@Entity(name = "book")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bookid")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "des")
	private String des;

	@Column(name = "author")
	private String author;

	@Column(name = "price")
	private int price;

	

	@Column(name = "image")
	private String image;

	@Column(name = "amount")
	private int amount;

	@Column(name = "promotion")
	private float promotion;

	@Column(name="typeid")
	private int typeId;
	
	@Column(name="isdelete")
	private boolean isDelete;
	
	@Column(name="status")
	private String status;

	public Book(String name, String des, String author, int price, String image, int amount, float promotion,
			int typeId) {
		super();
		this.name = name;
		this.des = des;
		this.author = author;
		this.price = price;
		this.image = image;
		this.amount = amount;
		this.promotion = promotion;
		this.typeId = typeId;
	}

}
