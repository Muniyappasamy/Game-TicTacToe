package com.bnpp.fortis.kata.booksdevelopment.controller;

import com.bnpp.fortis.kata.booksdevelopment.dto.Book;
import com.bnpp.fortis.kata.booksdevelopment.dto.BookDto;
import com.bnpp.fortis.kata.booksdevelopment.dto.CartSummaryReportDto;
import com.bnpp.fortis.kata.booksdevelopment.service.BooksDevelopmentService;
import com.bnpp.fortis.kata.booksdevelopment.service.BooksDevelopmentServiceImp;
import com.bnpp.fortis.kata.booksdevelopment.service.PriceSummationService;
import com.bnpp.fortis.kata.booksdevelopment.service.PriceSummationServiceImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(value = "BooksDevelopmentRestController", description = "REST APIs related to Book Development Price Calculation !!!!")

@RestController
@RequestMapping("${developmentbooks.controller.path}")
@RequiredArgsConstructor
public class BooksDevelopmentController {


    private final BooksDevelopmentService booksDevelopmentService;


    private final PriceSummationService priceSummationService;

    @ApiOperation(value = "Get list of Books in the Store ", response = Iterable.class, tags = "getAllBooks")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping("${developmentbooks.endpoints.getallbooks}")
    public List<Book> getAllBooks() {
        return booksDevelopmentService.getAllBooks();
    }

    @ApiOperation(value = "Get list of Students in the System ", response = CartSummaryReportDto.class, tags = "calculateDiscountPrice")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 400, message = "Bad Request Given Input is not Matching the Store Boooks!"),
            @ApiResponse(code = 404, message = "not found!!!")
            })
    @PostMapping("${developmentbooks.endpoints.calculateprice}")
    public CartSummaryReportDto calculateDiscountPrice(@RequestBody List<BookDto> listOfBooks) {
        return priceSummationService .calculateDiscountPrice(listOfBooks);
    }

}
