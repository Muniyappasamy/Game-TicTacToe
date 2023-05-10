package com.bnpp.fortis.kata.booksdevelopment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartSummaryReportDto {
    @ApiModelProperty(notes = "Group Of Classified Book", name = "listOfBookGroupClassifications", required = true, value = "test list of book groups ")
    private List<BookGroupClassification> listOfBookGroupClassifications;

    @ApiModelProperty(notes = "Actual price of Books", name = "actualPrice", required = true, value = "test actualPrice")
    private double actualPrice;

    @ApiModelProperty(notes = "Total Discount for the Books", name = "totalDiscount", required = true, value = "test totalDiscount")
    private double totalDiscount;

    @ApiModelProperty(notes = "BestPrice post discount for the Books", name = "bestPrice", required = true, value = "test bestPrice")
    private double bestPrice;
}
