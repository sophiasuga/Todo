package com.example.todo.domain;

import java.time.LocalDateTime;

public class Todo {

    private Long id;
    private String title;
    private boolean completed;
    private LocalDateTime createdAt;

    private Todo() {}

    public static Todo create(String title) {
        validate(title);
        Todo todo = new Todo();
        todo.title = title;
        todo.completed = false;
        todo.createdAt = LocalDateTime.now();
        return todo;
    }

    public static Todo reconstruct(Long id, String title, boolean completed, LocalDateTime createdAt) {
        Todo todo = new Todo();
        todo.id = id;
        todo.title = title;
        todo.completed = completed;
        todo.createdAt = createdAt;
        return todo;
    }

    public void changeTitle(String title) {
        validate(title);
        this.title = title;
    }

    public void complete() {
        this.completed = true;
    }

    public void undoComplete() {
        this.completed = false;
    }

    private static void validate(String title) {
        if (title == null || title.isBlank()) throw new IllegalArgumentException("タイトルは必須です");
        if (title.length() > 255) throw new IllegalArgumentException("タイトルは255文字以内にしてください");
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public boolean isCompleted() { return completed; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
