package com.example.todo.resolver;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class HealthResolver {

    @QueryMapping
    public String health() {
        return "GraphQL is working!";
    }
}
