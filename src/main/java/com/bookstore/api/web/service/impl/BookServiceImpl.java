package com.bookstore.api.web.service.impl;

import com.bookstore.api.web.dto.MutationResponse;
import com.bookstore.api.web.dto.Rate;
import com.bookstore.api.web.persistence.entity.Author;
import com.bookstore.api.web.persistence.entity.Book;
import com.bookstore.api.web.persistence.entity.BookRate;
import com.bookstore.api.web.persistence.entity.Rating;
import com.bookstore.api.web.persistence.entity.aggregation.AverageRateResult;
import com.bookstore.api.web.persistence.entity.embedded.Catalog;
import com.bookstore.api.web.persistence.repository.AuthorRepository;
import com.bookstore.api.web.persistence.repository.BookRateRepository;
import com.bookstore.api.web.persistence.repository.BookRepository;
import com.bookstore.api.web.persistence.template.BookRateCustomTemplate;
import com.bookstore.api.web.service.BookService;
import com.google.common.collect.Lists;
import io.leangen.graphql.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gregorio on 20/11/17.
 */
@Service
public class BookServiceImpl implements BookService{

    @Autowired
     BookRepository bookRepository;

    @Autowired
     AuthorRepository authorRepository;

    @Autowired BookRateRepository bookRateRepository;

    @Autowired BookRateCustomTemplate bookRateCustomTemplate;

    @GraphQLQuery(name = "allBooks")
    public List<Book> allBooks( @GraphQLArgument(name = "skip", defaultValue = "1") Integer skip,
        @GraphQLArgument(name = "first", defaultValue = "1") Integer first ){
        final Pageable pageableRequest = new PageRequest(skip, first);
        Page<Book> bookPage = bookRepository.findAll(pageableRequest);
        return Lists.newArrayList(bookPage);
    }

    @GraphQLQuery(name = "authorList")
    public List<Author> getBookAuthors(@GraphQLContext Book book){
        List<String> ids = new ArrayList<String>();
        for(Catalog author : book.getAuthors()){
            ids.add(author.getId());
        }
        return authorRepository.findByIdIn(ids);
    }

    @GraphQLMutation(name = "rateBook")
    public MutationResponse rateBook(@GraphQLArgument(name = "rateInput") @GraphQLNonNull Rate rate){

        bookRateRepository.save(new BookRate(rate.getRate(), rate.getId()));
        Book book = bookRepository.findOne(rate.getId());
        AverageRateResult averageRateResult = bookRateCustomTemplate.getAverageRating(rate.getId());
        book.setRating(new Rating(averageRateResult.getAvg(), averageRateResult.getCount()));
        bookRepository.save(book);
        return new MutationResponse(true);
    }
}
