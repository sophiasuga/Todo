package com.example.todo.domain;

import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class TodoMapper {

    private final DateTimeFormatter formatter;

    public TodoMapper(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    public com.example.todo.generated.Todo toTodo(Todo domainTodo) {
        String createdAt = domainTodo.getCreatedAt() != null
                ? domainTodo.getCreatedAt().format(formatter)
                : null;
        return new com.example.todo.generated.Todo(
            String.valueOf(domainTodo.getId()),
            domainTodo.getTitle(),
            domainTodo.isCompleted(),
            createdAt
        );
    }
}
