package com.bookstore.api.web.persistence.entity;

import org.springframework.data.annotation.Id;

/**
 * Created by Gregorio on 09/11/17.
 */
abstract class Rate {

    @Id
    private String id;
    private Integer rate;

    public Rate() {
    }

    public Rate(Integer rate) {
        this.rate = rate;
    }

    public Rate(String id, Integer rate) {
        this.id = id;
        this.rate = rate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
