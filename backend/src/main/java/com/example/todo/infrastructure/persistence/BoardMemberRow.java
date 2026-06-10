package com.example.todo.infrastructure.persistence;

import java.util.Date;

public class BoardMemberRow extends BoardMemberRowKey {
    private String role;

    private Date joinedAt;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public Date getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Date joinedAt) {
        this.joinedAt = joinedAt;
    }
}