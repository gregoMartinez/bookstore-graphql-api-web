package com.bookstore.api.web.dto;

/**
 * Created by Gregorio on 15/11/17.
 */
public class MutationResponse {

    private Boolean ok;

    public MutationResponse() {
    }

    public MutationResponse(Boolean ok) {

        this.ok = ok;
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }
}
