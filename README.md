# Todo App

Spring Boot + React + PostgreSQL + GraphQL + Spring Security

## 起動手順

### 1. DB起動（Docker）

```bash
docker-compose up -d
```

### 2. バックエンド起動

```bash
cd backend
./mvnw spring-boot:run
```

- API: http://localhost:8080/api/health
- GraphiQL: http://localhost:8080/graphiql

### 3. フロントエンド起動

```bash
cd frontend
npm install
npm run dev
```

- App: http://localhost:3000

## 技術スタック

| レイヤー | 技術 |
|---|---|
| Backend | Spring Boot 3 / Spring GraphQL / Spring Security / JPA |
| Frontend | React 18 / TypeScript / Vite / Apollo Client |
| DB | PostgreSQL 16 |
| インフラ | Docker / Docker Compose |
