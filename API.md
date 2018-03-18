# CONTaFin - API REST Documentation

## About our API
All you can find in our API Rest is information about users, loans, fines (penalties), resources, types, copies and genres. All you need to do is simply; you have to follow the API rules. If you try to do following a different way, it's probably what you will recieve an error.

## How to use our API
1. Download [Postman](https://www.getpostman.com/).
2. You only can send GET, POST, PUT and DELETE requests.

## API requests
### Resources
The resource API has GET, POST, PUT and DELETE methods.
http: // localhost: 8080 followed by the containt request URL.
All API queries have been preceded by /api

### Admin
These must be preceded by /Admin. All methods linked to Admin will return the same answers except those indicated.

* ##### Success Response:

	* HttpStatus.OK

* ##### Error Response:

	* Code: 404 NOT FOUND
  
#### Obtain user data
Get a table with the different users and their data.

* ##### URL

	< /UserData >

* ##### Método:

	`GET`
  
* ##### Success Response:

  {
  }
  
* ##### Error Response:
	* Code: HttpStatus.UNAUTHORIZED
  
#### Export data from all users

Export all users to an excel to analyze their data

* ##### URL

	< /UserData/Export >

* ##### Método:

	`GET`

* ##### Error Response:
	* Code: HttpStatus.INTERNAL_SERVER_ERROR

### Users
These must be preceded by /User. All methods linked to User will return the same answers except those indicated.

* ##### Success Response:

	* HttpStatus.OK

* ##### Error Response:

	* Code: HttpStatus.UNAUTHORIZED Or HttpStatus.BAD_REQUEST
 
#### Resource to obtain a user
The data of the logged in user is obtained

* ##### URL

	< />

* ##### Método:

	`GET`
  
* ##### Error Response:

	* Code: Only HttpStatus.UNAUTHORIZED
  
#### Resource to modify a user
The name of the logged in user is modified

* ##### URL

	< /Name>

* ##### Método:

	`PUT`
  
* ##### URL Params

	* Required:

		`Name=[string]`

#### Resource to modify a user's email
The email of a logged in user is modified
* ##### URL

	< /Email>

* ##### Método:

	`PUT`
  
* ##### URL Params

 * Required:

	`Email=[string]`
  
#### Resource to modify a user's password
The password of a logged in user is modified

* ##### URL

	< /Password>

* ##### Método:

	`PUT`
  
* ##### URL Params

 * Required:

	`Password=[string]`

#### Resource to modify a user's goal
The goal of a logged in user is modified

* ##### URL

	< /Goal>

* ##### Método:

	`PUT`
  
* ##### URL Params

 * Required:

	`Goal=[string]`
  
#### Resource to upload a photo
Allow upload a photo associated with that logged in user

* ##### URL

	< /Photo>

* ##### Método:

	`POST`
  
* ##### URL Params

 * Required:

	`Photo=[root]`
  
* ##### Error Response:

	* Code: Only HttpStatus.UNAUTHORIZED Or HttpStatus.INTERNAL_SERVER_ERROR
  
#### Resource to delete a user
It allows to eliminate the profile of the logged in user

* ##### URL

	< />

* ##### Método:

	`DELETE`
  
* ##### Error Response:

	* Code: HttpStatus.NOT_FOUND
  
### Login
All methods linked to User will return the same answers except those indicated.

* ##### Success Response:

	* HttpStatus.OK

* ##### Error Response:

	* Code: HttpStatus.UNAUTHORIZED

#### Resource login
Allows a user to log in.Allows a user to log in.

* ##### URL

	< /login>

* ##### Método:

	`GET`
  
#### resource loguot  
Allows a user to log out.

* ##### URL

	< /logout>

* ##### Método:

	`GET`
  
### Lessons
These must be preceded by /Unit. All methods linked to User will return the same answers except those indicated.

* ##### Success Response:

	* HttpStatus.OK

* ##### Error Response:

	* Code: HttpStatus.NOT_FOUND

#### Resource to show all the lessons
It allows to show all the lessons.

* ##### URL

	< />

* ##### Método:

	`GET`
 
#### Resource to show the unit with this lesson
Allows showing the lessons of a specific unit.

* ##### URL

	< /{idunit}/Lesson/>

* ##### Método:

	`GET`
  
* Required:

  `idunit=[long]`
  
#### Resource to show a lesson
Allows you to show a specific lesson.

* ##### URL

	< /{idunit}/Lesson/{id}>

* ##### Método:

	`GET`
  
* Required:

  `idunit=[long]`
  
#### Resource to know if a lesson is completed
Let us know if a lesson is complete or not.

* ##### URL

	< /{idunit}/Lesson/{idlesson}/Completed>

* ##### Método:

	`GET`
  
* Required:

  `idunit=[long]`
  `idlesson=[long]`
  
#### Resource to modify a lesson
Allows you to modify a specific lesson
* ##### URL

	< /{idunit}/Lesson/{id}>

* ##### Método:

	`PUT`
  
* Required:

  `idunit=[long]`
  `id=[long]`

#### Resource to obtain a unit
It allows to obtain a unit

* ##### URL

	< /{id}>

* ##### Método:

	`GET`
 
* Required:

  `id=[long]`
  
#### Resource to create a unit
Create a unit

* ##### URL

	< /{id}>

* ##### Método:

	`POST`
  
* Required:

  `id=[long]`
 
* ##### Success Response:

	* HttpStatus.CREATED

#### Resource to modify a unit
Allows you to modify a unit

* ##### URL

	< /{id}>

* ##### Método:

	`PUT`

* Required:

  `id=[long]`
  
#### Resource to remove a unit
Allows you to delete a unit

* ##### URL

	< /{id}>

* ##### Método:

	`DELETE`

* Required:

  `id=[long]`
  
#### Resource to show all the exercises
Show all exercises

* ##### URL

	< /Lesson/Exercises/>

* ##### Método:

	`GET`

### Lesson
These must be preceded by /Unit/{idunit}/Lesson/{idlesson}. All methods linked to User will return the same answers except those indicated.

* ##### Required:

  `idunit=[long]`
  `idlesson=[long]`
  
* ##### Success Response:

	* HttpStatus.OK

* ##### Error Response:

	* Code: HttpStatus.NOT_FOUND
  
#### Resource to show an exercise
It allows to show all the exercises of a lesson

* ##### URL

	< /Exercise/{id}>

* ##### Método:

	`GET`
  
* ##### Required:

  `id=[long]`
  
#### Resource to modify an exercise
Allows you to modify an exercise of a specific lesson

* ##### URL

	< /Exercise/{id}>

* ##### Método:

	`PUT`
  
* ##### Required:

  `id=[long]`
  
#### Resource to get the answer of the exercise
It allows to obtain the answer of a specific exercise

* ##### URL

	< /Exercise/{id}/Answer>

* ##### Método:

	`GET`
  
* ##### Required:

  `id=[long]`
  
#### Resource to modify a response for an exercise
Allows you to modify the response of a specific exercise

* ##### URL

	< /Exercise/{id}/Answer>

* ##### Método:

	`PUT` 

* ##### Required:

  `id=[long]`
  

