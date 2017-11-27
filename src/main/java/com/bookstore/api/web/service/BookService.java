package com.bookstore.api.web.service;

import com.bookstore.api.web.persistence.entity.Author;
import com.bookstore.api.web.persistence.entity.Book;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;

import java.util.List;

/**
 * Created by Gregorio on 20/11/17.
 */
public interface BookService {

    public List<Author> getBookAuthors(@GraphQLContext Book book);

    public List<Book> allBooks( @GraphQLArgument(name = "skip", defaultValue = "0") Integer skip,
        @GraphQLArgument(name = "first", defaultValue = "0") Integer first );
}
