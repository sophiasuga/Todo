# Backend ライブラリ仕様

## 実行環境

| 項目 | バージョン |
|---|---|
| Java | 21 (Microsoft OpenJDK) |
| Spring Boot | 3.3.0 |
| ビルドツール | Maven 3.9.6 |
| 実行方式 | Docker コンテナ |

---

## 依存ライブラリ

### Web
| ライブラリ | バージョン | 用途 |
|---|---|---|
| spring-boot-starter-web | 3.3.0 | REST API / Tomcat 組み込み |

### GraphQL
| ライブラリ | バージョン | 用途 |
|---|---|---|
| spring-boot-starter-graphql | 3.3.0 | GraphQL エンドポイント (`/graphql`) |
| spring-graphql-test | 3.3.0 | GraphQL テスト用 |

- GraphiQL UI: http://localhost:8080/graphiql
- スキーマ定義: `src/main/resources/graphql/schema.graphqls`
- Java Record 自動生成: `codegen/generate-records.groovy` (GMavenPlus)

### データベース
| ライブラリ | バージョン | 用途 |
|---|---|---|
| spring-boot-starter-data-jpa | 3.3.0 | ORM / Repository パターン |
| mssql-jdbc | (BOM管理) | SQL Server JDBC ドライバ |
| liquibase-core | (BOM管理) | DB マイグレーション管理 |

- DB: SQL Server 2022 (Docker)
- マイグレーション: `src/main/resources/db/changelog/`
- `ddl-auto: none`（Liquibase が完全管理）

### セキュリティ
| ライブラリ | バージョン | 用途 |
|---|---|---|
| spring-boot-starter-security | 3.3.0 | 認証・認可 (Phase 3 で本格実装) |
| spring-boot-starter-validation | 3.3.0 | Bean Validation (@NotNull 等) |

### ユーティリティ
| ライブラリ | バージョン | 用途 |
|---|---|---|
| lombok | (BOM管理) | ボイラープレートコード削減 |
| spring-boot-devtools | (BOM管理) | ホットリロード (開発時のみ) |

### コード生成
| ライブラリ | バージョン | 用途 |
|---|---|---|
| gmavenplus-plugin | 3.0.2 | Groovy スクリプト実行 |
| groovy | 4.0.21 | schema.graphqls → Java Record 生成 |
| build-helper-maven-plugin | (BOM管理) | 生成ソースをコンパイルパスに追加 |

---

## パッケージ構成

```
com.example.todo
├── TodoApplication.java
├── config/
│   └── SecurityConfig.java
├── controller/
│   └── HealthController.java      # GET /api/health
├── generated/                     # 自動生成 Record (target/ 以下)
│   ├── Todo.java
│   ├── CreateTodoInput.java
│   └── UpdateTodoInput.java
└── resolver/
    ├── HealthResolver.java
    └── TodoResolver.java
```
