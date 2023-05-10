package com.bnpp.fortis.kata.booksdevelopment.service;

import com.bnpp.fortis.kata.booksdevelopment.dto.Book;
import com.bnpp.fortis.kata.booksdevelopment.dto.BookDto;
import com.bnpp.fortis.kata.booksdevelopment.dto.CartSummaryReportDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PriceSummationService {

	public CartSummaryReportDto calculateDiscountPrice(List<BookDto> listOfBooks);

}
