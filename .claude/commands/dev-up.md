---
description: Docker Desktop 起動 → バックエンド+DB → フロントエンドを一括起動する
allowed-tools: Bash, PowerShell
---

開発環境を一括起動してください。手順は以下の通りです。

## 1. Docker Desktop の起動確認

`docker info` を実行して Docker エンジンが動いているか確認する。

動いていない場合は PowerShell で Docker Desktop を起動する：

```powershell
Start-Process "C:\Program Files\Docker\Docker\Docker Desktop.exe"
```

その後、`docker info` が成功するまで 10 秒間隔でリトライする（最大 12 回 = 約2分）。
2分待っても起動しない場合はエラーとしてユーザーに報告して中断する。

## 2. バックエンド + DB の起動

リポジトリルート（docker-compose.yml がある場所）で、バックグラウンド実行する：

```bash
docker compose up --build -d
```

ビルドエラーが出た場合はログを確認してユーザーに報告する。

起動後、ヘルスチェック確認：

```bash
docker compose ps
```

backend と db が Up になっていることを確認する。backend の起動には DB のヘルスチェック待ちで1分程度かかることがある。

## 3. フロントエンドの起動

`frontend/` ディレクトリで dev サーバーをバックグラウンド実行する（run_in_background: true を使う）：

```bash
cd frontend && pnpm dev
```

## 4. 完了報告

すべて起動したら以下を表に整理してユーザーに報告する：

| サービス | URL |
|---|---|
| フロントエンド | http://localhost:3000 |
| GraphiQL | http://localhost:8080/graphiql |
| GraphQL エンドポイント | http://localhost:8080/graphql |

注意事項：
- すでに起動済みのサービスはスキップしてその旨を報告する
- ポート競合（3000 / 8080 / 1433）が起きた場合は占有プロセスを調べて報告する（勝手に kill しない）
