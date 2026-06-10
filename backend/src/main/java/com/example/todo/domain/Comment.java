package com.example.todo.domain;

import java.time.LocalDateTime;

public class Comment {

    private Long id;
    private Long cardId;
    private Long userId;
    private String content;
    private LocalDateTime createdAt;

    public Comment(Long cardId, Long userId, String content) {
        validateContent(content);
        this.cardId = cardId;
        this.userId = userId;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    // ビジネスロジック: 投稿者本人かどうか
    public boolean isPostedBy(Long userId) {
        return this.userId.equals(userId);
    }

    // ビジネスロジック: 内容を編集（本人のみ）
    public void edit(Long requestUserId, String newContent) {
        if (!isPostedBy(requestUserId)) {
            throw new IllegalStateException("他のユーザーのコメントは編集できません");
        }
        validateContent(newContent);
        this.content = newContent;
    }

    private void validateContent(String content) {
        if (content == null || content.isBlank()) throw new IllegalArgumentException("コメント内容は必須です");
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getCardId() { return cardId; }
    public Long getUserId() { return userId; }
    public String getContent() { return content; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
