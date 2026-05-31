# --- Java 21 のセットアップ ---
$java21Path = "C:\Program Files\Microsoft\jdk-21.0.11.10-hotspot"
$env:JAVA_HOME = $java21Path
$env:PATH = "$java21Path\bin;$env:PATH"

# --- Maven のセットアップ ---
$mavenHome = "$env:USERPROFILE\.maven\apache-maven-3.9.6"
$mavenBin  = "$mavenHome\bin\mvn.cmd"

if (-not (Test-Path $mavenBin)) {
    Write-Host "Maven not found. Downloading..." -ForegroundColor Yellow
    $mavenZip = "$env:TEMP\maven.zip"
    Invoke-WebRequest -Uri "https://archive.apache.org/dist/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip" `
        -OutFile $mavenZip -UseBasicParsing
    Expand-Archive -Path $mavenZip -DestinationPath "$env:USERPROFILE\.maven" -Force
    Remove-Item $mavenZip
    Write-Host "Maven installed." -ForegroundColor Green
}

$env:PATH = "$mavenHome\bin;$env:PATH"

# --- DB 起動 ---
Write-Host "Starting DB..." -ForegroundColor Cyan
Set-Location $PSScriptRoot
docker-compose up -d

Write-Host "Waiting for PostgreSQL to be ready..."
$maxRetry = 30
$retry = 0
do {
    $healthy = docker inspect todo-postgres --format "{{.State.Health.Status}}" 2>$null
    if ($healthy -eq "healthy") { break }
    $retry++
    if ($retry -ge $maxRetry) {
        Write-Host "DB did not become healthy in time." -ForegroundColor Red
        exit 1
    }
    Start-Sleep -Seconds 2
} while ($true)

# --- バックエンド起動 ---
Write-Host "DB is ready. Starting backend..." -ForegroundColor Green
Set-Location "$PSScriptRoot\backend"
& $mavenBin spring-boot:run
