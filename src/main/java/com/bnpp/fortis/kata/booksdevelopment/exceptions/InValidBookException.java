package com.bnpp.fortis.kata.booksdevelopment.exceptions;

import java.util.List;

public class InValidBookException  extends RuntimeException{

    public InValidBookException(List<String> invalidBooks){
        super("Books which are given not in Book Store: " + invalidBooks);
    }
}
