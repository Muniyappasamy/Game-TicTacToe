package com.bnpp.fortis.kata.booksdevelopment.controller;

import com.bnpp.fortis.kata.booksdevelopment.dto.Book;
import com.bnpp.fortis.kata.booksdevelopment.dto.BookDto;
import com.bnpp.fortis.kata.booksdevelopment.dto.CartSummaryReportDto;
import com.bnpp.fortis.kata.booksdevelopment.service.BooksDevelopmentService;
import com.bnpp.fortis.kata.booksdevelopment.service.PriceSummationService;
import com.bnpp.fortis.kata.booksdevelopment.service.PriceSummationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${developmentbooks.controller.path}")
public class BooksDevelopmentController {

	@Autowired // @required arg constructor
	private BooksDevelopmentService booksDevelopmentService;

	@Autowired
	private PriceSummationService priceSummationServiceImp;

	@GetMapping("${developmentbooks.endpoints.getallbooks}")
	public List<Book> getAllBooks() {
		return booksDevelopmentService.getAllBooks();
	}

	@PostMapping("${developmentbooks.endpoints.calculateprice}")
	public CartSummaryReportDto calculateDiscountPrice(@RequestBody List<BookDto> listOfBooks) {
		return priceSummationServiceImp.calculateDiscountPrice(listOfBooks);
	}

}
