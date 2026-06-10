package com.example.todo.infrastructure.persistence;

public class CardAssigneeRowKey {
    private Long cardId;

    private Long userId;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}