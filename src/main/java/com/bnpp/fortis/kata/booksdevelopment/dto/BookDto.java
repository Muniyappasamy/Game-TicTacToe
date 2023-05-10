package com.bnpp.fortis.kata.booksdevelopment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
	@ApiModelProperty(notes = "Name of the Book",name="name",required=true,value="test book name")
	private String name;

	@ApiModelProperty(notes = "Quantity of the Book",name="quantity",required=true,value="test quantity")
	private int quantity;
}
