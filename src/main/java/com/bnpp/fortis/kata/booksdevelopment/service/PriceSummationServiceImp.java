package com.bnpp.fortis.kata.booksdevelopment.service;

import com.bnpp.fortis.kata.booksdevelopment.dto.BookDto;
import com.bnpp.fortis.kata.booksdevelopment.dto.BookGroupClassification;
import com.bnpp.fortis.kata.booksdevelopment.dto.CartSummaryReportDto;
import com.bnpp.fortis.kata.booksdevelopment.exceptions.InValidBookException;
import com.bnpp.fortis.kata.booksdevelopment.storerepository.BookDevelopmentStackDetails;
import com.bnpp.fortis.kata.booksdevelopment.storerepository.DiscountDetails;
import com.bnpp.fortis.kata.booksdevelopment.utils.BookCalculatePriceServiceUtils;
import com.bnpp.fortis.kata.booksdevelopment.utils.Constants;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PriceSummationServiceImp  implements  PriceSummationService{

	@Autowired
	BookCalculatePriceServiceUtils bookCalculatePriceServiceUtils;
	@Override
	public CartSummaryReportDto calculateDiscountPrice(List<BookDto> listOfBooks) {
		bookCalculatePriceServiceUtils.validateBooks(listOfBooks);
		Map<String, Integer> listOfBooksWithQuantity = listOfBooks.stream()
				.collect(Collectors.toMap(BookDto::getName, BookDto::getQuantity));
		List<Integer> listOfApplicableDiscounts = bookCalculatePriceServiceUtils.getApplicableDiscounts(listOfBooksWithQuantity.size());
		CartSummaryReportDto priceSummaryDto = new CartSummaryReportDto();
		if (CollectionUtils.isNotEmpty(listOfApplicableDiscounts)) {
			bookCalculatePriceServiceUtils.calculatePriceWithDiscount(listOfBooksWithQuantity, listOfApplicableDiscounts, priceSummaryDto);
		} else {
			bookCalculatePriceServiceUtils.calculatePriceWithOutDiscount(listOfBooksWithQuantity, priceSummaryDto);
		}
		return priceSummaryDto;
	}


}