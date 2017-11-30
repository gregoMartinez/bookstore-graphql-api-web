package com.bookstore.api.web.persistence.template;

import com.bookstore.api.web.persistence.entity.Genre;

/**
 * Created by Gregorio on 29/11/17.
 */
public interface GenreCustomTemplate {

    public Genre getRandomGenre(Integer randomSkip);
}
