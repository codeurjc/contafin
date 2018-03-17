Write-Output "Building image of contafin"
docker build --no-cache -t contafin/contafin:latest .

Write-Output "Pushing image to Dockerhub"
docker push contafin/contafin:latest