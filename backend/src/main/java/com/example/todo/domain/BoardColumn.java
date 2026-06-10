package com.example.todo.domain;

public class BoardColumn {

    private Long id;
    private Long boardId;
    private String title;
    private int position;
    private String color;

    private static final int MAX_CARDS = 50;
    private int cardCount;

    public BoardColumn(Long boardId, String title, int position) {
        validateTitle(title);
        this.boardId = boardId;
        this.title = title;
        this.position = position;
    }

    // ビジネスロジック: カードを追加できるか
    public boolean canAcceptCard() {
        return cardCount < MAX_CARDS;
    }

    // ビジネスロジック: 列名変更
    public void rename(String newTitle) {
        validateTitle(newTitle);
        this.title = newTitle;
    }

    private void validateTitle(String title) {
        if (title == null || title.isBlank()) throw new IllegalArgumentException("列名は必須です");
        if (title.length() > 100) throw new IllegalArgumentException("列名は100文字以内にしてください");
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getBoardId() { return boardId; }
    public String getTitle() { return title; }
    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public int getCardCount() { return cardCount; }
    public void setCardCount(int cardCount) { this.cardCount = cardCount; }
}
