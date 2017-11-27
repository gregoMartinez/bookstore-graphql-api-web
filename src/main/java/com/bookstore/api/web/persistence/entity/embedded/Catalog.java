package com.bookstore.api.web.persistence.entity.embedded;

import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * Created by Gregorio on 13/11/17.
 */
@Document
public class Catalog implements Serializable{

    @Field("id")
    private String id;
    private String name;

    public Catalog() {
    }

    public Catalog(String id, String name) {
        this.id = id;
        this.name = name;
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
}
