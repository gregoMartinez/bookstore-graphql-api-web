package com.bookstore.api.web.persistence.template;

import com.bookstore.api.web.persistence.entity.aggregation.AverageRateResult;

/**
 * Created by Gregorio on 20/11/17.
 */
public interface BookRateCustomTemplate {

    public AverageRateResult getAverageRating(String bookId);
}
