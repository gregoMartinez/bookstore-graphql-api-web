package com.bookstore.api.web.controller;

import com.bookstore.api.web.service.AuthorService;
import com.bookstore.api.web.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.query.PublicResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gregorio on 20/03/17.
 */
@RestController
public class GraphQLRestController {

    private final GraphQL graphQlFromAnnotated;

    @Autowired
    public GraphQLRestController(BookService bookService, AuthorService authorService){
        //Schema generated from query classes
        GraphQLSchema schemaFromAnnotated = new GraphQLSchemaGenerator()
            .withResolverBuilders(
                //Resolve by annotations
                new AnnotatedResolverBuilder(),
                //Resolve public methods inside root package
                new PublicResolverBuilder("com.bookstore.api.web"))
            .withOperationsFromSingletons(bookService, authorService)
            .withValueMapperFactory(new JacksonValueMapperFactory())
            .generate();
        graphQlFromAnnotated = GraphQL.newGraphQL(schemaFromAnnotated).build();
    }

    @PostMapping(value = "/graphql", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Object indexFromAnnotated(@RequestBody Map<String, Object> request) throws IOException {

        ExecutionResult executionResult = null;

            if(request.get("variables") != null){

                executionResult = graphQlFromAnnotated
                    .execute((String) request.get("query"), (String) request.get("operationName"),
                        (Object) null, (Map<String, Object>) request.get("variables"));
            }else{

                executionResult = graphQlFromAnnotated
                    .execute((String) request.get("query"), (String) request.get("operationName"),
                        (Object) null);
            }

        return executionResult;
    }

}
