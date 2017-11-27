package com.bookstore.api.web.persistence.entity;

import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Gregorio on 09/11/17.
 */
@Document(collection = "author")
public class Author {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private Rating rating;

    public Author() {
    }

    public Author(String id, String name, Rating rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    public Author(String name, Rating rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
}

