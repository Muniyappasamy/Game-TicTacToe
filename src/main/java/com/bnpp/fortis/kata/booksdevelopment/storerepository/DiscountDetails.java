package com.bnpp.fortis.kata.booksdevelopment.storerepository;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum DiscountDetails {
	TWO_DIFFERENT_BOOKS(2, 5),
	THREE_DIFFERENT_BOOKS(3, 10),
	FOUR_DIFFERENT_BOOKS(4, 20),
	FIVE_DIFFERENT_BOOKS(5, 25);

	private int numberOfDistinctItems;
	private int discountPercentage;
	public static Map<Integer, Integer> discountMap = Stream.of( values() ).collect( Collectors.toMap(k -> k.numberOfDistinctItems, v -> v.discountPercentage ) );

}