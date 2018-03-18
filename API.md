# CONTaFin - API REST Documentation

## About our API
All you can find in our API Rest is information about users, loans, fines (penalties), resources, types, copies and genres. All you need to do is simply; you have to follow the API rules. If you try to do following a different way, it's probably what you will recieve an error.

## How to use our API
1. Download [Postman](https://www.getpostman.com/).
2. You only can send GET, POST, PUT and DELETE requests.

## API requests
### Resources
The resource API has GET, POST, PUT and DELETE methods.
The URLs can be sent by typing
http: // localhost: 8080 followed by the containt request URL in the following tables.

#### LoginRestController

|Type|Request description|Request URL|Success response|Error response|
|----|-------------------|-----------|----------------|--------------|
|1|Resource for logging|/api/login|HttpStatus.OK|HttpStatus.UNAUTHORIZED|
|2|Resource for logout|/api/logout|HttpStatus.OK|HttpStatus.UNAUTHORIZED|

#### LoginRestController

|Type|Request description|Request URL|Success response|Error response|
|----|-------------------|-----------|----------------|--------------|
|1|Resource for logging|/api/login|HttpStatus.OK|HttpStatus.UNAUTHORIZED|
|2|Resource for logout|/api/logout|HttpStatus.OK|HttpStatus.UNAUTHORIZED|

#### AdminRestController

|Type|Request description|Request URL|Success response|Error response|
|----|-------------------|-----------|----------------|--------------|
|GET|Resource to show user data|api/Admin/UserData|HttpStatus.OK|HttpStatus.UNAUTHORIZED|
|GET|Resource to export user data|api/Admin//UserData/Export|HttpStatus.OK|HttpStatus.INTERNAL_SERVER_ERROR|

#### UserRestController

|Type|Request description|Request URL|Success response|Error response|
|----|-------------------|-----------|----------------|--------------|
|DELETE|Resource to delete a user|/api/User/|HttpStatus.OK|HttpStatus.NOT_FOUND|
|GET|Resource to obtain a user|/api/User/|HttpStatus.OK|HttpStatus.UNAUTHORIZED|
|PUT|Resource to modify the username|/api/User/Name|HttpStatus.OK|HttpStatus.UNAUTHORIZED / HttpStatus.BAD_REQUEST|
|PUT|Resource to modify the user's email|/api/User/Email|HttpStatus.OK|HttpStatus.UNAUTHORIZED / HttpStatus.BAD_REQUEST|
|PUT|Resource to modify the user's password|/api/User/Password|HttpStatus.OK|HttpStatus.UNAUTHORIZED / HttpStatus.BAD_REQUEST|
|POST|Resource to add a photograph|/api/User/Photo|HttpStatus.OK|HttpStatus.UNAUTHORIZED / HttpStatus.BAD_REQUEST / HttpStatus.INTERNAL_SERVER_ERROR|
|PUT|Resource to modify the user's goal|/api/User/Goal|HttpStatus.OK|HttpStatus.UNAUTHORIZED / HttpStatus.BAD_REQUEST|

#### LessonRestController

|Type|Request description|Request URL|Success response|Error response|
|----|-------------------|-----------|----------------|--------------|
|GET|Resource to show all the lessons|/api/Unit/Lessons/| | |
|GET|Resource to show the unit with this lesson|/api/Unit/{idunit}/Lesson/|HttpStatus.OK|HttpStatus.NOT_FOUND|
|GET|Resource to show a lesson|/api/Unit/{idunit}/Lesson/{id}|HttpStatus.OK|HttpStatus.NOT_FOUND|
|PUT|Resource to modify a lesson|/api/Unit/{idunit}/Lesson/{id}|HttpStatus.OK|HttpStatus.NOT_FOUND|
|GET|Resource to know if a lesson is completed|/api/Unit/{idunit}/Lesson/{idlesson}/Completed|HttpStatus.OK| |

#### UnitRestController

|Type|Request description|Request URL|Success response|Error response|
|----|-------------------|-----------|----------------|--------------|
|GET|Resource to get all the units|/api/Unit/| | |
|GET|Resource to obtain a unit|/api/Unit/{id}|HttpStatus.OK|HttpStatus.NOT_FOUND|
|POST|Resource to create a unit|/api/Unit/|HttpStatus.CREATED| |
|PUT|Resource to modify a unit|/api/Unit/{id}|HttpStatus.OK|HttpStatus.NOT_FOUND|
|DELETE|Resource to remove a unit|/api/Unit/{id}/Completed|HttpStatus.OK|HttpStatus.NOT_FOUND|

#### ExerciseRestController

|Type|Request description|Request URL|Success response|Error response|
|----|-------------------|-----------|----------------|--------------|
|GETresource to show all the exercises|/api/Unit/Lesson/Exercises/| | |
|GET|resource to show an exercise|/api/Unit/{idunit}/Lesson/{idlesson}/Exercise/{id}|HttpStatus.OK|HttpStatus.NOT_FOUND|
|PUT|resource to modify an exercise|/api/Unit/{idunit}/Lesson/{idlesson}/Exercise/{id}|HttpStatus.OK|HttpStatus.NOT_FOUND|
|GET|resource to get the answer of the exercise|/api/Unit/{idunit}/Lesson/{idlesson}/Exercise/{id}/Answer|HttpStatus.OK|HttpStatus.NOT_FOUND|
|PUT|resource to modify a response for an exercise|/api/Unit/{idunit}/Lesson/{idlesson}/Exercise/{id}/Answer|HttpStatus.OK|HttpStatus.NOT_FOUND|
|GET|resource to modify a solution for an exercise|/api/Unit//{idunit}/Lesson/{idlesson}/Exercise/{id}/Solution|HttpStatus.OK|HttpStatus.NOT_FOUND|
