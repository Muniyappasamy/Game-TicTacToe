package com.bnpp.fortis.kata.booksdevelopment.service.impl;

import com.bnpp.fortis.kata.booksdevelopment.dto.Book;
import com.bnpp.fortis.kata.booksdevelopment.dto.BookMapper;
import com.bnpp.fortis.kata.booksdevelopment.service.BooksDevelopmentService;
import com.bnpp.fortis.kata.booksdevelopment.storerepository.BookDevelopmentStackDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class BooksDevelopmentServiceImpl implements BooksDevelopmentService {



	@Autowired
	BookMapper bookMapper;



	@Override
	public List<Book> getAllBooks() {
		return Arrays.stream(BookDevelopmentStackDetails.values()).map(bookEnum -> bookMapper.mapper(bookEnum))
				.collect(Collectors.toList());
	}
}
