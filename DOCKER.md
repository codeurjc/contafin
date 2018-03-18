### Docker usage
Docker is required for running Contafin application. Yoy can download it [here](https://docs.docker.com/install/#supported-platforms). 

- To run the app you simply have to open a terminal in Docker folder and execute "docker-compose up".
To access the app you have to open your navigator and go to https://localhost:8080 .

- To stop the app you have to execute "docker-compose down".

- The docker-compose.yml file uses a mysql image sets up in port 3306 (Don't forget to stop your local mysql), and a contafin image, sets up in port 8080.


#### Scripts (Only for Windows)
With the scripts you can build the contafin image in a easy way and publish it in the dockerhub repo.

To execute the scripts you can simply use the right button and execute with powershell. You need to give docker 
permissions to manipulate the disk C. You have to go to Docker settings -> Shared Drives -> Mark C -> Apply.

Scripts:

- create_image.ps1 : builds the image of the contafin application using the Dockerfile. First the jar file is packaged using a docker maven image of dockerhub. RECOMENDATION: delete contafin-0.0.1-SNAPSHOT.jar before start the script.

- publish_image.ps1 publishes the image into the dockerhub repo. You need to be logged whit the contafin account.



