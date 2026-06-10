# インフラ仕様

## Docker 構成

### コンテナ一覧

| コンテナ名 | イメージ | ポート | 用途 |
|---|---|---|---|
| todo-sqlserver | mcr.microsoft.com/mssql/server:2022-latest | 1433 | SQL Server DB |
| todo-backend | (ローカルビルド) | 8080 | Spring Boot API |

### 起動方法

```powershell
.\start.ps1
```

内部処理：
1. SQL Server コンテナ起動
2. ヘルスチェック待機（最大180秒）
3. `todo_db` データベース作成（存在しない場合）
4. バックエンドイメージをビルド＆起動

### docker-compose.yml の依存関係

```
backend
  └── depends_on (healthy)
        └── db (SQL Server)
```

---

## SQL Server 設定

| 項目 | 値 |
|---|---|
| エディション | Express |
| データベース名 | todo_db |
| ユーザー | sa |
| パスワード | Todo_Pass123! |
| 接続文字列 | `jdbc:sqlserver://db:1433;databaseName=todo_db;encrypt=false;trustServerCertificate=true` |

---

## バックエンド Dockerfile

マルチステージビルド：

```
Stage 1 (build): maven:3.9.6-eclipse-temurin-21
  └── mvn dependency:go-offline
  └── mvn package -DskipTests

Stage 2 (runtime): eclipse-temurin:21-jre-jammy
  └── app.jar をコピーして実行
```

---

## Liquibase マイグレーション

| ファイル | 内容 |
|---|---|
| 001-create-users.yaml | users テーブル |
| 002-create-boards.yaml | boards テーブル |
| 003-create-board-columns.yaml | board_columns テーブル |
| 004-create-cards.yaml | cards テーブル |
| 005-create-labels.yaml | labels テーブル |
| 006-create-card-labels.yaml | card_labels 中間テーブル |

新しいマイグレーション追加手順：
1. `007-xxx.yaml` を `migrations/` に作成
2. `db.changelog-master.yaml` に `include` を追加
3. コンテナ再起動で自動適用
