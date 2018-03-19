Write-Output "Building image of contafin"
docker build -t contafin/contafin .

Write-Output "Pushing image to Dockerhub"
docker push contafin/contafin