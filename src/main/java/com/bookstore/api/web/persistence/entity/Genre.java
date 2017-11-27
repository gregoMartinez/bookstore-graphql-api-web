package com.bookstore.api.web.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Gregorio on 10/11/17.
 */
@Document(collection = "genre")
public class Genre {

   @Id
   private String id;
   private String name;

    public Genre() {
    }

    public Genre(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Genre(String name) {
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
