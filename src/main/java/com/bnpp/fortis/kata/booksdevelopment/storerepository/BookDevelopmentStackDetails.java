package com.bnpp.fortis.kata.booksdevelopment.storerepository;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookDevelopmentStackDetails {

	CLEAN_CODE(1,"Clean Code", "Robert Martin", 50.00),
	THE_CLEAN_CODER(2,"The Clean Coder", "Robert Martin",  50.00),
	CLEAN_ARCHITECTURE( 3,"Clean Architecture", "Robert Martin",  50.00),
	TEST_DRIVEN_DEVELOPMENT_BY_EXAMPLE( 4,"Test-Driven Development By Example", "Kent Beck",  50.00),
	WORKING_EFFECTIVELY_WITH_LEGACY_CODE( 5,"Working Effectively With Legacy Code", "Michael C. Feathers", 50.00);

	private int bookId;
	private String bookTitle;
	private String author;
	private double price;


}