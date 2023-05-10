package com.bnpp.fortis.kata.booksdevelopment.service;

import com.bnpp.fortis.kata.booksdevelopment.dto.Book;
import com.bnpp.fortis.kata.booksdevelopment.storerepository.BookDevelopmentStackDetails;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BooksDevelopmentServiceImp  implements  BooksDevelopmentService{

	@Autowired
	private ModelMapper modelMapper; // map struct


	@Override
	public List<Book> getAllBooks() {
		return Arrays.stream(BookDevelopmentStackDetails.values()).map(bookEnum -> modelMapper.map(bookEnum, Book.class))
				.collect(Collectors.toList());
	}
}
