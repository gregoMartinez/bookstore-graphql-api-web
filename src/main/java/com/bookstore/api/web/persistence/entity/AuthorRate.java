package com.bookstore.api.web.persistence.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Gregorio on 09/11/17.
 */
@Document(collection = "author_rate")
public class AuthorRate extends Rate{

    @Indexed
    private String authorId;

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

}
