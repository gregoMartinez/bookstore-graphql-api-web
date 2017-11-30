package com.bookstore.api.load;

import com.bookstore.api.web.context.SpringBootGraphqlApplication;
import com.bookstore.api.web.persistence.entity.*;
import com.bookstore.api.web.persistence.entity.embedded.Catalog;
import com.bookstore.api.web.persistence.repository.*;
import com.bookstore.api.web.persistence.template.GenreCustomTemplate;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Gregorio on 13/11/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringBootGraphqlApplication.class)
@TestPropertySource(locations="classpath:application.properties")
public class TestLoadData {

    @Autowired  BookRepository bookRepository;

    @Autowired  AuthorRepository authorRepository;

    @Autowired  GenreRepository genreRepository;

    @Autowired  GenreCustomTemplate genreCustomTemplate;

    @Autowired  BookRateRepository bookRateRepository;

    @Autowired  AuthorRateRepository authorRateRepository;

    public void truncateCollections(){

        bookRepository.deleteAll();
        authorRepository.deleteAll();
        genreRepository.deleteAll();
        bookRateRepository.deleteAll();
        authorRateRepository.deleteAll();

    }

    //@Ignore
    @Test
    public void testLoadBooks(){

        truncateCollections();

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("load_files/genres.csv");

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                String[] lineSplitted = line.split(cvsSplitBy);
                Genre genre = new Genre(lineSplitted[0]);
                genreRepository.save(genre);
                System.out.println("Genre [name= " + lineSplitted[0] + "]");

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        is = classloader.getResourceAsStream("load_files/books.csv");
         br = null;
         line = "";
         cvsSplitBy = ";";
        int i = 1;

        try {

            br = new BufferedReader(new InputStreamReader(is));
            long count = genreRepository.count();

            while ((line = br.readLine()) != null && i <= 2000) {

                Double randomSkip = Math.floor(Math.random() * count);

                String[] lineSplitted = line.split(cvsSplitBy);
                if(!lineSplitted[10].equals("")  && !lineSplitted[7].equals("") && !lineSplitted[5].equals("") && !lineSplitted[8].equals("")){

                    double start = 100;
                    double end = 1500;
                    double random = new Random().nextDouble();
                    double result = start + (random * (end - start));

                    List<Catalog> authorList = new ArrayList<Catalog>();
                    String[] authorsNames = lineSplitted[7].split(",");

                    for(String name: authorsNames){
                        Author author = authorRepository.findByName(name);
                        if(author != null){
                            Catalog bookAuthor = new Catalog(author.getId(), author.getName());
                            authorList.add(bookAuthor);
                        }else{
                            Author keyAuthor = authorRepository.save(new Author(name, new Rating(0.0, 0L)));
                            Catalog bookAuthor = new Catalog(keyAuthor.getId(), name);
                            authorList.add(bookAuthor);
                            System.out.println("Author [name= " + name+ "]");
                        }
                    }

                    Genre genre = genreCustomTemplate.getRandomGenre(randomSkip.intValue());

                    Book book = new Book();
                    book.setIsbn(lineSplitted[5]);
                    book.setTitle(lineSplitted[10]);
                    book.setAuthorsNames(lineSplitted[7]);
                    book.setPublicationYear(Integer.valueOf(lineSplitted[8]));
                    book.setRating(new Rating(0.0, 0L));
                    book.setImageUrl(new ImageUrl(lineSplitted[21],lineSplitted[22]));
                    book.setPrice(result);
                    book.setGenre(new Catalog(genre.getId(), genre.getName()));
                    book.setAuthors(authorList);

                    try{
                        bookRepository.save(book);
                        System.out.println("--> Book [name= " + lineSplitted[10] + " line ="+i+"]");
                    }catch (Exception ex){
                        System.out.println("----> Error [ " + ex.getLocalizedMessage() + "]");
                    }

                    i++;
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
