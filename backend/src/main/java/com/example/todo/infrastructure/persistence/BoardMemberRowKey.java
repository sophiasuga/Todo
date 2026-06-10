package com.example.todo.infrastructure.persistence;

public class BoardMemberRowKey {
    private Long boardId;

    private Long userId;

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}