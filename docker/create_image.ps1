$var = $(get-item ${PWD}).parent.FullName
$pathProject = $var + "\contafin"
$pathJar = $pathProject + "\target"

Write-Output "Creating jar of contafin..."
#Create jar contafin
docker run -it --rm --name contafin -v ${pathProject}:/usr/src/mymaven -w /usr/src/mymaven maven mvn clean package -DskipTests

#Move jar to actual directory
mv ${pathJar}/contafin-0.0.1-SNAPSHOT.jar .

Write-Output "Creating Docker image of contafin..."
#Creating image 
docker build -t jaimemorillo/contafin .