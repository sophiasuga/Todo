package com.example.todo.domain;

import java.time.LocalDateTime;

public class User {

    private Long id;
    private String email;
    private String passwordHash;
    private String displayName;
    private LocalDateTime createdAt;

    public User(String email, String passwordHash, String displayName) {
        validateEmail(email);
        this.email = email;
        this.passwordHash = passwordHash;
        this.displayName = displayName;
        this.createdAt = LocalDateTime.now();
    }

    // ビジネスロジック: 表示名変更
    public void changeDisplayName(String newName) {
        if (newName == null || newName.isBlank()) throw new IllegalArgumentException("表示名は必須です");
        if (newName.length() > 100) throw new IllegalArgumentException("表示名は100文字以内にしてください");
        this.displayName = newName;
    }

    // ビジネスロジック: パスワード変更
    public void changePassword(String newPasswordHash) {
        if (newPasswordHash == null || newPasswordHash.isBlank()) throw new IllegalArgumentException("パスワードは必須です");
        this.passwordHash = newPasswordHash;
    }

    private void validateEmail(String email) {
        if (email == null || email.isBlank()) throw new IllegalArgumentException("メールアドレスは必須です");
        if (!email.contains("@")) throw new IllegalArgumentException("メールアドレスの形式が正しくありません");
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public String getDisplayName() { return displayName; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
