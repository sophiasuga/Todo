Set-Location $PSScriptRoot

# --- 1. DB だけ先に起動 ---
Write-Host "Starting SQL Server..." -ForegroundColor Cyan
docker-compose up -d db

# --- 2. SQL Server が healthy になるまで待機 ---
Write-Host "Waiting for SQL Server to be ready..."
$maxRetry = 60
$retry = 0
do {
    $healthy = docker inspect todo-sqlserver --format "{{.State.Health.Status}}" 2>$null
    if ($healthy -eq "healthy") { break }
    $retry++
    if ($retry -ge $maxRetry) {
        Write-Host "SQL Server did not become healthy in time." -ForegroundColor Red
        exit 1
    }
    Start-Sleep -Seconds 3
} while ($true)
Write-Host "SQL Server is ready." -ForegroundColor Green

# --- 3. todo_db を作成 ---
Write-Host "Creating database..." -ForegroundColor Yellow
docker exec todo-sqlserver /opt/mssql-tools18/bin/sqlcmd `
    -S localhost -U sa -P "Todo_Pass123!" -C `
    -Q "IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'todo_db') CREATE DATABASE todo_db"
Write-Host "Database ready." -ForegroundColor Green

# --- 4. バックエンドをビルド&起動 ---
Write-Host "Building and starting backend..." -ForegroundColor Cyan
docker-compose up --build -d backend

Write-Host ""
Write-Host "All services started!" -ForegroundColor Green
Write-Host "  API Health : http://localhost:8080/api/health"
Write-Host "  GraphiQL   : http://localhost:8080/graphiql"
Write-Host ""
Write-Host "Logs: docker-compose logs -f backend"
