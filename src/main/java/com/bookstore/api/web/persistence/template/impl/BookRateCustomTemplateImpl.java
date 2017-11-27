package com.bookstore.api.web.persistence.template.impl;

import com.bookstore.api.web.persistence.entity.BookRate;
import com.bookstore.api.web.persistence.entity.aggregation.AverageRateResult;
import com.bookstore.api.web.persistence.template.BookRateCustomTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

/**
 * Created by Gregorio on 20/11/17.
 */
@Repository
public class BookRateCustomTemplateImpl implements BookRateCustomTemplate{

    @Autowired
    MongoTemplate mongoTemplate;

    public AverageRateResult getAverageRating(String bookId){

        Aggregation agg = newAggregation(
            match(Criteria.where("bookId").is(bookId)),
            group().count().as("count").avg("rate").as("avg")
        );
        AggregationResults<AverageRateResult> groupResults
            = mongoTemplate.aggregate(agg, BookRate.class, AverageRateResult.class);
        List<AverageRateResult> result = groupResults.getMappedResults();

        return result.get(0);

    }
}
