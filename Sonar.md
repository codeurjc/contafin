# Installation guide and sonarqube test
Brief installation guide where we apply the steps followed for the correct creation of the analysis environment sonarqube in windows 10
## Elements used
- [Sonarqube 7.0](https://www.sonarqube.org/)
- [SonarQube Scanner](https://docs.sonarqube.org/display/SCAN/Analyzing+with+SonarQube+Scanner)
- [java jdk 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [MYSQL serverm5.7](https://dev.mysql.com/downloads/mysql/)
- [MYSQL Workbench 6.3](https://www.mysql.com/products/workbench/)
- [Chrome](https://www.google.es/chrome/index.html)
## Instalation
### Step 1
Install all previously mentioned elements
### Step 2
Using MYSQL Workbench we create an schema which we will call sonarqube, we can create it directly as the image shows or first create it and then use the scrip on it.

![](https://github.com/Caumel/DAW-G11-2018/blob/master/Screenshots%20Phase%205/SQInstall1.PNG)

### Step 3
Now we go to the conf file that is where we installed our sonarqube and we decompose and modify the following sections.

![](https://github.com/Caumel/DAW-G11-2018/blob/master/Screenshots%20Phase%205/SQInstall2.PNG)

![](https://github.com/Caumel/DAW-G11-2018/blob/master/Screenshots%20Phase%205/SQInstall3.PNG)

![](https://github.com/Caumel/DAW-G11-2018/blob/master/Screenshots%20Phase%205/SQInstall4.PNG)

### Step 4
Now we will start our sonarqube server, first we open a cmd where we will put the route to our sonarqube, then we will enter the bin folder, we will choose our operating system and once this is done we will copy this route in the cmd and execute StartSonar.bat

![](https://github.com/Caumel/DAW-G11-2018/blob/master/Screenshots%20Phase%205/SQInstall5.PNG)

![](https://github.com/Caumel/DAW-G11-2018/blob/master/Screenshots%20Phase%205/SQInstall6.PNG)

Now we can enter our LocalHost: 9000 where the server will be deployed

![](https://github.com/Caumel/DAW-G11-2018/blob/master/Screenshots%20Phase%205/SQInstall7.PNG)

### Step 5
We will enter our server sonarqube using the credentials:
- username: admin
- pass: admin
Where we can see our projects, configure them, eliminate them or install plugins.

![](https://github.com/Caumel/DAW-G11-2018/blob/master/Screenshots%20Phase%205/SQInstall8.PNG)

### Step 6
We create a file called sonar-project.properties which will have the following appearance

![](https://github.com/Caumel/DAW-G11-2018/blob/master/Screenshots%20Phase%205/SQInstall9.PNG)

The important data are "sonar.projectKey=my:project" and "sonar.projectName=My project" in which we will have to add the name of our project.
The file in our case will look like this.

![](https://github.com/Caumel/DAW-G11-2018/blob/master/Screenshots%20Phase%205/SQInstall10.PNG)

And this file will be stored in the raid of our project.

![](https://github.com/Caumel/DAW-G11-2018/blob/master/Screenshots%20Phase%205/SQInstall11.PNG)

### Step 7
The next step is to execute the analysis in our code.
For this we will open a cmd and we will place ourselves in the directory of the sonar-project and in the we will execute the sonar-scanner command

![](https://github.com/Caumel/DAW-G11-2018/blob/master/Screenshots%20Phase%205/SQInstall12.PNG)

When the analysis finishes, the following will appear and we will go to the sonarquide server page

![](https://github.com/Caumel/DAW-G11-2018/blob/master/Screenshots%20Phase%205/SQInstall13.PNG)

![](https://github.com/Caumel/DAW-G11-2018/blob/master/Screenshots%20Phase%205/SQFunction1.PNG)

### Step 8
Seeing our mistakes we can solve them.

![](https://github.com/Caumel/DAW-G11-2018/blob/master/Screenshots%20Phase%205/SQFunction2.PNG)

![](https://github.com/Caumel/DAW-G11-2018/blob/master/Screenshots%20Phase%205/SQFunction3.PNG)

![](https://github.com/Caumel/DAW-G11-2018/blob/master/Screenshots%20Phase%205/SQFunction4.PNG)

![](https://github.com/Caumel/DAW-G11-2018/blob/master/Screenshots%20Phase%205/SQFunction5.PNG)

![](https://github.com/Caumel/DAW-G11-2018/blob/master/Screenshots%20Phase%205/SQFunction6.PNG)

![](https://github.com/Caumel/DAW-G11-2018/blob/master/Screenshots%20Phase%205/SQFunction7.PNG)

![](https://github.com/Caumel/DAW-G11-2018/blob/master/Screenshots%20Phase%205/SQFunction8.PNG)

![](https://github.com/Caumel/DAW-G11-2018/blob/master/Screenshots%20Phase%205/SQFunction9.PNG)
