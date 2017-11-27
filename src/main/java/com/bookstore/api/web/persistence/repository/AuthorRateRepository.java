package com.bookstore.api.web.persistence.repository;

import com.bookstore.api.web.persistence.entity.AuthorRate;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Gregorio on 03/10/17.
 */
public interface AuthorRateRepository extends MongoRepository<AuthorRate, String>{

}
