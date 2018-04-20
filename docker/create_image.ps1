#Get the paths
$var = $(get-item ${PWD}).parent.FullName
$pathProject = $var + "\contafin"
$pathJar = $pathProject + "\target"
$pathAngular = $var + "\AngularContafin"

#Create Angular with Angular-cli image
Write-Output "Creating angular of contafin..."
docker run -it --rm --name contafinAngular -v ${pathAngular}:/opt/contafin -w /opt/contafin teracy/angular-cli ng build --base-href /new/

#Move angular to Spring contafin folder
Remove-Item ${pathProject}/src/main/resources/static/new/* -Recurse
Copy-Item ${pathAngular}/dist/* -Destination ${pathProject}/src/main/resources/static/new -Recurse


#Create jar contafin with Maven image
Write-Output "Creating jar of contafin..."
docker run -it --rm --name contafin -v ${pathProject}:/usr/src/mymaven -w /usr/src/mymaven maven mvn clean package -DskipTests

#Move jar to actual directory, delete if exist
$contafinJar = "contafin-0.0.1-SNAPSHOT.jar"
Remove-Item ${contafinJar}
Move-Item ${pathJar}/contafin-0.0.1-SNAPSHOT.jar .

#Creating image 
Write-Output "Creating Docker image of contafin..."
docker build -t contafin/contafin .