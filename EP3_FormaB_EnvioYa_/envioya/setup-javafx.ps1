$ErrorActionPreference = "Stop"

function Write-Step {
    param([string]$Message)
    Write-Host ""
    Write-Host "==> $Message" -ForegroundColor Cyan
}

function Write-OK {
    param([string]$Message)
    Write-Host "[OK] $Message" -ForegroundColor Green
}

function Write-Warn {
    param([string]$Message)
    Write-Host "[!] $Message" -ForegroundColor Yellow
}

function Write-Fail {
    param([string]$Message)
    Write-Host "[ERROR] $Message" -ForegroundColor Red
}

$JAVA_HOME_PATH = "C:\Program Files\Java\jdk-21"
$MAVEN_HOME_PATH = "C:\apache-maven-3.9.11"
$PROJECT_PATH = $PSScriptRoot
$POM_PATH = Join-Path $PROJECT_PATH "pom.xml"
$VSCODE_PATH = Join-Path $PROJECT_PATH ".vscode"
$SETTINGS_PATH = Join-Path $VSCODE_PATH "settings.json"

Clear-Host
Write-Host "============================================================" -ForegroundColor Cyan
Write-Host "       ENVIOYA - JAVA + MAVEN + JAVAFX BOOTSTRAP" -ForegroundColor Cyan
Write-Host "============================================================" -ForegroundColor Cyan

Write-Step "Configurando Java 21"
if (-not (Test-Path $JAVA_HOME_PATH)) {
    Write-Fail "No se encontro Java 21 en $JAVA_HOME_PATH"
    exit 1
}
$env:JAVA_HOME = $JAVA_HOME_PATH
if ($env:Path -notlike "*$JAVA_HOME_PATH\bin*") {
    $env:Path = "$JAVA_HOME_PATH\bin;$env:Path"
}
Write-OK "JAVA_HOME = $env:JAVA_HOME"

Write-Step "Configurando Maven 3.9.11"
if (-not (Test-Path $MAVEN_HOME_PATH)) {
    Write-Fail "No se encontro Maven en $MAVEN_HOME_PATH"
    exit 1
}
$env:MAVEN_HOME = $MAVEN_HOME_PATH
if ($env:Path -notlike "*$MAVEN_HOME_PATH\bin*") {
    $env:Path = "$MAVEN_HOME_PATH\bin;$env:Path"
}
Write-OK "MAVEN_HOME = $env:MAVEN_HOME"

Write-Step "Verificando proyecto Maven"
if (-not (Test-Path $POM_PATH)) {
    Write-Fail "No se encontro pom.xml en $POM_PATH"
    exit 1
}
Write-OK "Proyecto encontrado en $PROJECT_PATH"

if (-not (Test-Path $VSCODE_PATH)) {
    New-Item -ItemType Directory -Path $VSCODE_PATH | Out-Null
}

Set-Location $PROJECT_PATH
Write-Step "Compilando proyecto con mvn clean compile"
mvn clean compile
if ($LASTEXITCODE -ne 0) {
    Write-Fail "La compilacion fallo."
    exit 1
}
Write-OK "Proyecto compilado correctamente. Ejecuta: mvn javafx:run"
