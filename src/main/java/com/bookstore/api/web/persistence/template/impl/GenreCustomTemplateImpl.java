package com.bookstore.api.web.persistence.template.impl;

import com.bookstore.api.web.persistence.entity.Genre;
import com.bookstore.api.web.persistence.template.GenreCustomTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Gregorio on 29/11/17.
 */
@Repository
public class GenreCustomTemplateImpl implements GenreCustomTemplate{

    @Autowired private MongoTemplate mongoTemplate;

    public Genre getRandomGenre(Integer randomSkip){

        Query query = new Query();
        query.limit(1);
        query.skip(randomSkip);
        List<Genre> genres = mongoTemplate.find(query, Genre.class);
        return genres.isEmpty()?null:genres.get(0);
    }
}
