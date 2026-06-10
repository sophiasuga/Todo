package com.example.todo.infrastructure.persistence;

public class CardLabelRowKey {
    private Long cardId;

    private Long labelId;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }
}