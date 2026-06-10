package com.example.todo.domain;

import java.time.LocalDateTime;

public class Board {

    private Long id;
    private String title;
    private Long ownerId;
    private LocalDateTime createdAt;

    public Board(String title, Long ownerId) {
        validateTitle(title);
        this.title = title;
        this.ownerId = ownerId;
        this.createdAt = LocalDateTime.now();
    }

    // ビジネスロジック: 特定ユーザーがオーナーかどうか
    public boolean isOwnedBy(Long userId) {
        return this.ownerId.equals(userId);
    }

    // ビジネスロジック: タイトル変更
    public void rename(String newTitle) {
        validateTitle(newTitle);
        this.title = newTitle;
    }

    private void validateTitle(String title) {
        if (title == null || title.isBlank()) throw new IllegalArgumentException("ボード名は必須です");
        if (title.length() > 255) throw new IllegalArgumentException("ボード名は255文字以内にしてください");
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public Long getOwnerId() { return ownerId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
