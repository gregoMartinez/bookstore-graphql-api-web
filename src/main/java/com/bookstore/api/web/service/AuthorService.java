package com.bookstore.api.web.service;

import com.bookstore.api.web.persistence.entity.Author;
import com.bookstore.api.web.persistence.entity.Book;
import io.leangen.graphql.annotations.GraphQLContext;

import java.util.List;

/**
 * Created by Gregorio on 20/11/17.
 */
public interface AuthorService {

    public List<Book> getBookAuthors(@GraphQLContext Author author);
}
