package com.bnpp.fortis.kata.booksdevelopment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BookGroupClassification {
	private int discountPercentage;
	private double actualPrice;
	private double discount;
	private int numberOfBooks;
}
