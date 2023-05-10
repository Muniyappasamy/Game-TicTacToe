package com.bnpp.fortis.kata.booksdevelopment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
	private String id;
	private String name;
	private int quantity;
}
