package com.bnpp.fortis.kata.booksdevelopment.utils;

import com.bnpp.fortis.kata.booksdevelopment.dto.BookDto;
import com.bnpp.fortis.kata.booksdevelopment.dto.BookGroupClassification;
import com.bnpp.fortis.kata.booksdevelopment.dto.CartSummaryReportDto;
import com.bnpp.fortis.kata.booksdevelopment.exceptions.InValidBookException;
import com.bnpp.fortis.kata.booksdevelopment.storerepository.BookDevelopmentStackDetails;
import com.bnpp.fortis.kata.booksdevelopment.storerepository.DiscountDetails;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


