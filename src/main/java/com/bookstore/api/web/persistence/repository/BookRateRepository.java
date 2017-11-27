package com.bookstore.api.web.persistence.repository;

import com.bookstore.api.web.persistence.entity.BookRate;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Gregorio on 03/10/17.
 */
public interface BookRateRepository extends MongoRepository<BookRate, String>{

}
