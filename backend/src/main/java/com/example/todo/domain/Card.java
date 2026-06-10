package com.example.todo.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Card {

    private Long id;
    private Long columnId;
    private String title;
    private String description;
    private int position;
    private LocalDate dueDate;
    private Long createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Card(Long columnId, String title, int position, Long createdBy) {
        validateTitle(title);
        this.columnId = columnId;
        this.title = title;
        this.position = position;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // ビジネスロジック: 別の列に移動する
    public void moveTo(Long newColumnId, int newPosition) {
        if (newColumnId == null) throw new IllegalArgumentException("移動先の列は必須です");
        this.columnId = newColumnId;
        this.position = newPosition;
        this.updatedAt = LocalDateTime.now();
    }

    // ビジネスロジック: タイトル変更
    public void changeTitle(String newTitle) {
        validateTitle(newTitle);
        this.title = newTitle;
        this.updatedAt = LocalDateTime.now();
    }

    // ビジネスロジック: 期限設定
    public void setDueDate(LocalDate dueDate) {
        if (dueDate != null && dueDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("期限は今日以降の日付を指定してください");
        }
        this.dueDate = dueDate;
        this.updatedAt = LocalDateTime.now();
    }

    // ビジネスロジック: 期限切れかどうか
    public boolean isOverdue() {
        return dueDate != null && dueDate.isBefore(LocalDate.now());
    }

    private void validateTitle(String title) {
        if (title == null || title.isBlank()) throw new IllegalArgumentException("タイトルは必須です");
        if (title.length() > 255) throw new IllegalArgumentException("タイトルは255文字以内にしてください");
    }

    public Long getId() { return id; }
    public Long getColumnId() { return columnId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getPosition() { return position; }
    public LocalDate getDueDate() { return dueDate; }
    public Long getCreatedBy() { return createdBy; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setId(Long id) { this.id = id; }
}
