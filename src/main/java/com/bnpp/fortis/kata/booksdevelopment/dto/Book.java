package com.bnpp.fortis.kata.booksdevelopment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Book {
	private String bookTitle;
	private String author;
	private double price;
}
