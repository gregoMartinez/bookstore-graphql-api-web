package com.bookstore.api.web.persistence.repository;


import com.bookstore.api.web.persistence.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Created by Gregorio on 03/10/17.
 */
public interface BookRepository extends MongoRepository<Book, String>{

    public List<Book> findByAuthorsId(String id);
}
