package com.bookstore.api.web.service.impl;

import com.bookstore.api.web.persistence.entity.Author;
import com.bookstore.api.web.persistence.entity.Book;
import com.bookstore.api.web.persistence.repository.AuthorRepository;
import com.bookstore.api.web.persistence.repository.BookRepository;
import com.bookstore.api.web.service.AuthorService;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gregorio on 20/11/17.
 */
@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @GraphQLQuery(name = "books")
    public List<Book> getBookAuthors(@GraphQLContext Author author){
        return bookRepository.findByAuthorsId(author.getId());
    }

}
