package com.bookstore.api.web.persistence.repository;

import com.bookstore.api.web.persistence.entity.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Gregorio on 03/10/17.
 */
public interface AuthorRepository extends MongoRepository<Author, String>{

    public List<Author> findByIdIn(List<String> ids);

    public Author findByName(String name);
}
