package com.bookstore.api.web.persistence.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Gregorio on 09/11/17.
 */
@Document(collection = "book_rate")
public class BookRate extends Rate{

    @Indexed
    private String bookId;

    public BookRate(Integer rate, String bookId) {
        super(rate);
        this.bookId = bookId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
