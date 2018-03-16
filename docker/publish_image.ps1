Write-Output "Building image of contafin"
docker build -t jaimemorillo/contafin .

Write-Output "Pushing image to Dockerhub"
docker push jaimemorillo/contafin