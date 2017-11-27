package com.bookstore.api.web.persistence.entity;

import com.bookstore.api.web.persistence.entity.embedded.Catalog;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Gregorio on 09/11/17.
 */
@Document(collection = "book")
public class Book {

    @Id
    private String id;
    @Indexed(unique = true)
    private String isbn;
    private String title;
    private String authorsNames;
    private List<Catalog> authors;
    private Integer publicationYear;
    private Catalog genre;
    private Rating rating;
    private Double price;
    private ImageUrl imageUrl;

    public Book() {
    }

    public Book(String id, String isbn, String title, String authorsNames,
        List<Catalog> authors, Integer publicationYear, Catalog genre, Rating rating,
        Double price, ImageUrl imageUrl) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.authorsNames = authorsNames;
        this.authors = authors;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.rating = rating;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorsNames() {
        return authorsNames;
    }

    public void setAuthorsNames(String authorsNames) {
        this.authorsNames = authorsNames;
    }

    public List<Catalog> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Catalog> authors) {
        this.authors = authors;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Catalog getGenre() {
        return genre;
    }

    public void setGenre(Catalog genre) {
        this.genre = genre;
    }
    public ImageUrl getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(ImageUrl imageUrl) {
        this.imageUrl = imageUrl;
    }

}
