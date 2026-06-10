package com.example.todo.domain;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public List<Todo> getAllTodos() {
        return repository.findAll();
    }

    public Optional<Todo> getTodoById(String id) {
        return repository.findById(Long.parseLong(id));
    }

    public Todo createTodo(String title) {
        return repository.save(Todo.create(title));  // バリデーションは Todo.create() が担う
    }

    public Optional<Todo> updateTodo(String id, String title, Boolean completed) {
        return repository.findById(Long.parseLong(id)).map(todo -> {
            if (title != null) todo.changeTitle(title);
            if (completed != null && completed) todo.complete();
            if (completed != null && !completed) todo.undoComplete();
            return repository.save(todo);
        });
    }

    public boolean deleteTodo(String id) {
        return repository.delete(Long.parseLong(id));
    }
}
