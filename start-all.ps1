# Paths to your projects
$BACKEND_DIR = "E:\load-balancer\backend-server"
$LB_DIR = "E:\load-balancer\load-balancer"

Write-Host "Starting Backend Server 1 on port 8081..."
Start-Process powershell -ArgumentList "cd `"$BACKEND_DIR`"; mvn spring-boot:run -Dspring-boot.run.arguments='--server.port=8081 --backend.id=1'"

Write-Host "Starting Backend Server 2 on port 8082..."
Start-Process powershell -ArgumentList "cd `"$BACKEND_DIR`"; mvn spring-boot:run -Dspring-boot.run.arguments='--server.port=8082 --backend.id=2'"

Write-Host "Starting Backend Server 3 on port 8083..."
Start-Process powershell -ArgumentList "cd `"$BACKEND_DIR`"; mvn spring-boot:run -Dspring-boot.run.arguments='--server.port=8083 --backend.id=3'"

Write-Host "Starting Load Balancer on port 8080..."
Start-Process powershell -ArgumentList "cd `"$LB_DIR`"; mvn spring-boot:run"

Write-Host "`nAll services started in separate windows!"
