package com.bnpp.fortis.kata.booksdevelopment.service;

import com.bnpp.fortis.kata.booksdevelopment.dto.Book;
import com.bnpp.fortis.kata.booksdevelopment.storerepository.BookDevelopmentStackDetails;
import com.bnpp.fortis.kata.booksdevelopment.storerepository.DiscountDetails;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public interface BooksDevelopmentService {

	public List<Book> getAllBooks();

}
