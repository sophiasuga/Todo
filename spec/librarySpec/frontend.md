# Frontend ライブラリ仕様

## 実行環境

| 項目 | バージョン |
|---|---|
| Node.js | 24.x |
| パッケージマネージャ | pnpm 10.x |
| ビルドツール | Vite 5.x |
| 言語 | TypeScript 5.x |

---

## 依存ライブラリ

### コア
| ライブラリ | バージョン | 用途 |
|---|---|---|
| react | ^18.3.1 | UIフレームワーク |
| react-dom | ^18.3.1 | DOM レンダリング |
| typescript | ^5.4.5 | 型安全な JavaScript |

### GraphQL クライアント
| ライブラリ | バージョン | 用途 |
|---|---|---|
| @apollo/client | ^3.10.4 | GraphQL クライアント / キャッシュ管理 |
| graphql | ^16.8.2 | GraphQL コア (Apollo の peer dependency) |

- Apollo Provider: `src/main.tsx` でルートに設定
- GraphQL エンドポイント: `/graphql` (Vite proxy 経由でバックエンドへ)

### ルーティング
| ライブラリ | バージョン | 用途 |
|---|---|---|
| react-router-dom | ^6.23.1 | クライアントサイドルーティング |

### ビルド・開発
| ライブラリ | バージョン | 用途 |
|---|---|---|
| vite | ^5.2.13 | 開発サーバー / バンドラー |
| @vitejs/plugin-react | ^4.3.0 | React HMR / JSX 変換 |

### 型定義
| ライブラリ | バージョン | 用途 |
|---|---|---|
| @types/react | ^18.3.29 | React 型定義 |
| @types/react-dom | ^18.3.0 | ReactDOM 型定義 |

---

## Vite Proxy 設定

`vite.config.ts` でバックエンドへのリクエストをプロキシ：

```
/api/*    → http://localhost:8080
/graphql  → http://localhost:8080
```

開発時は `pnpm dev` (port 3000) で起動。CORS 設定不要。

---

## pnpm セキュリティ設定

`package.json` の `pnpm` フィールドで許可するビルドスクリプトを明示：

```json
"pnpm": {
  "onlyBuiltDependencies": ["esbuild"]
}
```

npm より安全：パッケージが互いのファイルにアクセスできない隔離管理。
