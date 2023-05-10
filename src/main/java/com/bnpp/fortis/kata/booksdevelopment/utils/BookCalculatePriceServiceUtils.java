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

@Component
public class BookCalculatePriceServiceUtils {

    public void calculatePriceWithOutDiscount(Map<String, Integer> listOfbooksWithQuantityMap,
                                              CartSummaryReportDto priceSummaryDto) {
        BookGroupClassification booksWithoutDiscount = getBookGroupWithoutDiscount(listOfbooksWithQuantityMap);
        List<BookGroupClassification> listOfBookGroupClassification = new ArrayList<>();
        listOfBookGroupClassification.add(booksWithoutDiscount);
        updateBestDiscount(priceSummaryDto, listOfBookGroupClassification);
    }

    public void calculatePriceWithDiscount(Map<String, Integer> listOfbooksWithQuantityMap,
                                           List<Integer> listOfApplicableDiscounts, CartSummaryReportDto priceSummaryDto) {
        listOfApplicableDiscounts.stream().forEach(numberOfBooksToGroup -> {
            Map<String, Integer> bookQuantityMapCopy = duplicateMap(listOfbooksWithQuantityMap);
            List<BookGroupClassification> listOfBookGroupClassification = getBookGroupswithDiscount(bookQuantityMapCopy, new ArrayList<>(),
                    numberOfBooksToGroup);
            if (CollectionUtils.isNotEmpty(bookQuantityMapCopy.keySet())) {
                BookGroupClassification booksWithoutDiscount = getBookGroupWithoutDiscount(bookQuantityMapCopy);
                listOfBookGroupClassification.add(booksWithoutDiscount);
            }
            updateBestDiscount(priceSummaryDto, listOfBookGroupClassification);
        });
    }

    private Map<String, Integer> duplicateMap(Map<String, Integer> bookQuantityMap) {
        return bookQuantityMap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (firstKey, secondKey) -> secondKey,
                        LinkedHashMap::new));
    }

    private void updateBestDiscount(CartSummaryReportDto priceSummaryDto, List<BookGroupClassification> listOfBookGroupClassification) {
        double discount = listOfBookGroupClassification.stream().mapToDouble(BookGroupClassification::getDiscount).sum();
        if (discount >= priceSummaryDto.getTotalDiscount()) {
            priceSummaryDto.setListOfBookGroupClassifications(listOfBookGroupClassification);
            double actualPrice = listOfBookGroupClassification.stream().mapToDouble(BookGroupClassification::getActualPrice).sum();
            priceSummaryDto.setActualPrice(actualPrice);
            priceSummaryDto.setTotalDiscount(discount);
            priceSummaryDto.setBestPrice(actualPrice - discount);
        }
    }

    public void validateBooks(List<BookDto> listOfBooks) {
        Map<String, Double> validBooks = getValidBooks(); //
        List<String> invalidBooks = listOfBooks.stream()
                .filter(book -> BooleanUtils.isFalse(validBooks.containsKey(book.getName()))).map(BookDto::getName) // change this to if
                .collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(invalidBooks)) {
            throw new InValidBookException(invalidBooks);
        }
    }

    private List<BookGroupClassification> getBookGroupswithDiscount(Map<String, Integer> listOfBooksWithQuantityMap,
                                                                    List<BookGroupClassification> bookGroupClassification, Integer numberOfBooksToGroup) {
        numberOfBooksToGroup = getNumberOfBooksToGroup(listOfBooksWithQuantityMap, numberOfBooksToGroup);
        Optional<DiscountDetails> discount = getDiscount(numberOfBooksToGroup);
        if (discount.isPresent()) {
            int bookToGroupBasedOnDiscount = discount.get().getNumberOfDistinctItems();
            List<String> listOfDistinctBooks = listOfBooksWithQuantityMap.keySet().stream().limit(bookToGroupBasedOnDiscount)
                    .collect(Collectors.toList());
            BookGroupClassification currentBookGroupClassification = getBookGroup(listOfDistinctBooks);
            bookGroupClassification.add(currentBookGroupClassification);
            removeDiscountedItems(listOfBooksWithQuantityMap, listOfDistinctBooks);
            getBookGroupswithDiscount(listOfBooksWithQuantityMap, bookGroupClassification, numberOfBooksToGroup);
        }
        return bookGroupClassification;
    }

    private int getNumberOfBooksToGroup(Map<String, Integer> bookQuantityMap, Integer numberOfBooksToGroup) {
        return numberOfBooksToGroup < bookQuantityMap.size() ? numberOfBooksToGroup : bookQuantityMap.size();
    }

    private BookGroupClassification getBookGroupWithoutDiscount(Map<String, Integer> listOfBooksWithQuantityMap) {
        Map<String, Double> bookPriceMap = getValidBooks();
        Set<String> uniqueBooks = listOfBooksWithQuantityMap.keySet();
        double actualPrice = uniqueBooks.stream()
                .mapToDouble(bookName -> bookPriceMap.get(bookName) * listOfBooksWithQuantityMap.get(bookName)).sum();
        int numberOfBooks = listOfBooksWithQuantityMap.values().stream().mapToInt(Integer::intValue).sum();
        return new BookGroupClassification(Constants.ZERO_PERCENT, actualPrice, Constants.NO_DISCOUNT, numberOfBooks);
    }

    public List<Integer> getApplicableDiscounts(int numberOfBooks) {
        return Arrays.stream(DiscountDetails.values()).sorted(Comparator.reverseOrder())
                .filter(discountGroup -> discountGroup.getNumberOfDistinctItems() <= numberOfBooks)
                .map(DiscountDetails::getNumberOfDistinctItems).collect(Collectors.toList());
    }

    private Optional<DiscountDetails> getDiscount(int numberOfBooks) {

        return Arrays.stream(DiscountDetails.values()).sorted(Comparator.reverseOrder())
                .filter(discountGroup -> discountGroup.getNumberOfDistinctItems() <= numberOfBooks).findFirst();
    }

    private BookGroupClassification getBookGroup(List<String> listOfBookToGroup) {
        Map<String, Double> bookIdPriceMap = getValidBooks();
        double actualPrice = listOfBookToGroup.stream().mapToDouble(bookName -> bookIdPriceMap.get(bookName) * Constants.ONE_QUANTITY)
                .sum();
        int discountPercentage = getDiscountPercentage(listOfBookToGroup.size());
        double discount = (actualPrice * discountPercentage) / Constants.HUNDRED;
        return new BookGroupClassification(discountPercentage, actualPrice, discount, listOfBookToGroup.size());

    }

    private Map<String, Double> getValidBooks() {
        return Arrays.stream(BookDevelopmentStackDetails.values())
                .collect(Collectors.toMap(BookDevelopmentStackDetails::getBookTitle, BookDevelopmentStackDetails::getPrice));
    }

    private void removeDiscountedItems(Map<String, Integer> listOfBookswithQuantityMap, List<String> discountedBooks) {
        discountedBooks.forEach(bookName -> {
            int bookQuantities = listOfBookswithQuantityMap.get(bookName);
            if (bookQuantities > Constants.ONE_QUANTITY) {
                listOfBookswithQuantityMap.put(bookName, bookQuantities - Constants.ONE_QUANTITY);
            } else {
                listOfBookswithQuantityMap.remove(bookName);
            }
        });
    }

    private int getDiscountPercentage(int numberOfDistinctBooks) {
        Optional<DiscountDetails> discount = getDiscount(numberOfDistinctBooks);
        return (discount.isPresent()) ? discount.get().getDiscountPercentage() : Constants.ZERO_PERCENT;
    }

}
