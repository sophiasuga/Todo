Set-Location $PSScriptRoot

$healthy = docker inspect todo-sqlserver --format "{{.State.Health.Status}}" 2>$null
if ($healthy -ne "healthy") {
    Write-Host "SQL Server is not running. Run .\start.ps1 first." -ForegroundColor Red
    exit 1
}

Write-Host "Running MyBatis Generator in Docker..." -ForegroundColor Cyan

$backendPath = (Resolve-Path "$PSScriptRoot\backend").Path

docker run --rm `
    --network todo_default `
    -v "${backendPath}:/app" `
    -v "mbg_maven_cache:/root/.m2" `
    -w /app `
    maven:3.9.6-eclipse-temurin-21 `
    mvn mybatis-generator:generate -q

Write-Host ""
Write-Host "Done!" -ForegroundColor Green
Write-Host "  Domain : backend/src/main/java/com/example/todo/domain/"
Write-Host "  Mapper : backend/src/main/java/com/example/todo/mapper/"
Write-Host "  XML    : backend/src/main/resources/mapper/"
