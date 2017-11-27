package com.bookstore.api.web.dto;

/**
 * Created by Gregorio on 15/11/17.
 */
public class Rate {

    private String id;
    private Integer rate;

    public Rate() {
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
