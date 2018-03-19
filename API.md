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

## Admin
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

## Users
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
  
## Login
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

## Unit
The following queries will be preceded by /Unit.

### Resource to show all the units

* ##### URL:

	< / >

* ##### Method:
	
	`GET`
	
* ##### URL Params:

	`Empty`
	
* ##### Example of query:

	* URL
		
		`/api/Unit/`
		
* ##### Success response:

	```
	{
	    "totalElements": 2,
            "totalPages": 1,
	    "number": 0,
	    "size": 20,
	    "first": true,
	    "last": true,
	    "content": [
		{
		    "id": 1,
		    "name": "Unidad 1"
		},
		{
		    "id": 2,
		    "name": "Unidad 2"
		}
	    ]
	}
	
	```

### Resource to show one unit

* ##### URL:

	< /{id} >

* ##### Method:
	
	`GET`
	
* ##### URL Params:

	`id=[long]`
	
* ##### Example of query:

	* URL
		
		`/api/Unit/1`
		
* ##### Success response:

	```
	{
	    "id": 1,
	    "name": "Unidad 1"
	}
	```
	
* ##### Error response:

	**Code:** 404 NOT FOUND


### Create Unit with is Lessons and Exercises

* ##### URL:

	< / >

* ##### Method:
	
	`POST`
	
* ##### URL Params:

	`Empty`
	
* ##### Example of query:

	* URL
		
		`/api/Unit/`

* ##### Data params:

	```
	{
	    "name": "Unidad 3",
	    "lessons": [
		{
		    "id": 7,
		    "name": "Lección 1 Unidad 3",
		    "exercises": [
			{
				"kind": 1,
				"statement": "Enunciado 1",
				"texts": [ "Opcion 1", "Opcion 2","Opcion 3" ],
				"answer": {
					"result": "dos"
				}
			},
			{
				"kind": 2,
				"statement": "Enunciado 2",
				"answer": {
					"result": "Este texto es de prueba"
				}
			},
			{
				"kind": 5,
				"statement": "Enunciado 3",
				"texts": [ "Opcion 1", "Opcion 2", "Opcion 3" ],
				"answer": {
					"result": "uno"
				}
			},
			{
				"kind": 7,
				"statement": "Enunciado 4",
				"texts": [ "Opcion 1", "Opcion 2", "Opcion 3" ],
				"answer": {
					"result": "tres"
				}
			}
		    ]
		},
		{
		    "id": 8,
		    "name": "Lección 2 Unidad 3",
		    "exercises": [
			{
				"kind": 1,
				"statement": "Enunciado 1",
				"texts": [ "Opcion 1", "Opcion 2", "Opcion 3" ],
				"answer": {
					"result": "dos"
				}
			},
			{
				"kind": 2,
				"statement": "Enunciado 2",
				"answer": {
					"result": "Este texto es de prueba"
				}
			},
			{
				"kind": 5,
				"statement": "Enunciado 3",
				"texts": [ "Opcion 1", "Opcion 2", "Opcion 3" ],
				"answer": {
					"result": "uno"
				}
			},
			{
				"kind": 7,
				"statement": "Enunciado 4",
				"texts": [ "Opcion 1", "Opcion 2", "Opcion 3" ],
				"answer": {
					"result": "tres"
				}
			}
		    ]
		},
		{
		    "id": 9,
		    "name": "Lección 3 Unidad 3",
		    "exercises": [
			{
				"kind": 1,
				"statement": "Enunciado 1",
				"texts": [ "Opcion 1", "Opcion 2", "Opcion 3" ],
				"answer": {
					"result": "dos"
				}
			},
			{
				"kind": 2,
				"statement": "Enunciado 2",
				"answer": {
					"result": "Este texto es de prueba"
				}
			},
			{
				"kind": 5,
				"statement": "Enunciado 3",
				"texts": [ "Opcion 1", "Opcion 2", "Opcion 3" ],
				"answer": {
					"result": "uno"
				}
			},
			{
				"kind": 7,
				"statement": "Enunciado 4",
				"texts": [ "Opcion 1", "Opcion 2", "Opcion 3" ],
				"answer": {
					"result": "tres"
				}
			}
		    ]
		}
	    ]
	}
	```

* ##### Success response:

	```
	{
	    "id": 3,
	    "name": "Unidad 3",
	    "lessons": [
		{
		    "id": 7,
		    "name": "Lección 1 Unidad 3",
		    "exercises": [
			{
			    "id": 25,
			    "kind": 1,
			    "statement": "Enunciado 1",
			    "texts": [
				"Opcion 1",
				"Opcion 2",
				"Opcion 3"
			    ]
			},
			{
			    "id": 26,
			    "kind": 2,
			    "statement": "Enunciado 2",
			    "texts": null
			},
			{
			    "id": 27,
			    "kind": 5,
			    "statement": "Enunciado 3",
			    "texts": [
				"Opcion 1",
				"Opcion 2",
				"Opcion 3"
			    ]
			},
			{
			    "id": 28,
			    "kind": 7,
			    "statement": "Enunciado 4",
			    "texts": [
				"Opcion 1",
				"Opcion 2",
				"Opcion 3"
			    ]
			}
		    ]
		},
		{
		    "id": 8,
		    "name": "Lección 2 Unidad 3",
		    "exercises": [
			{
			    "id": 29,
			    "kind": 1,
			    "statement": "Enunciado 1",
			    "texts": [
				"Opcion 1",
				"Opcion 2",
				"Opcion 3"
			    ]
			},
			{
			    "id": 30,
			    "kind": 2,
			    "statement": "Enunciado 2",
			    "texts": null
			},
			{
			    "id": 31,
			    "kind": 5,
			    "statement": "Enunciado 3",
			    "texts": [
				"Opcion 1",
				"Opcion 2",
				"Opcion 3"
			    ]
			},
			{
			    "id": 32,
			    "kind": 7,
			    "statement": "Enunciado 4",
			    "texts": [
				"Opcion 1",
				"Opcion 2",
				"Opcion 3"
			    ]
			}
		    ]
		},
		{
		    "id": 9,
		    "name": "Lección 3 Unidad 3",
		    "exercises": [
			{
			    "id": 33,
			    "kind": 1,
			    "statement": "Enunciado 1",
			    "texts": [
				"Opcion 1",
				"Opcion 2",
				"Opcion 3"
			    ]
			},
			{
			    "id": 34,
			    "kind": 2,
			    "statement": "Enunciado 2",
			    "texts": null
			},
			{
			    "id": 35,
			    "kind": 5,
			    "statement": "Enunciado 3",
			    "texts": [
				"Opcion 1",
				"Opcion 2",
				"Opcion 3"
			    ]
			},
			{
			    "id": 36,
			    "kind": 7,
			    "statement": "Enunciado 4",
			    "texts": [
				"Opcion 1",
				"Opcion 2",
				"Opcion 3"
			    ]
			}
		    ]
		}
	    ]
	}
	```
	
* ##### Error response:

	**Code:** 404 NOT FOUND

### Change the name of the unit

* ##### URL:

	< /{id} >

* ##### Method:
	
	`PUT`
	
* ##### URL Params:

	`id=[long]`
	
* ##### Example of query:

	* URL
		
		`/api/Unit/1`
		
* ##### Data Params:
	
	```
	{
		"name": "Unidad Prueba"
	}
	```

* ##### Success response:

	```
	{
	    "id": 1,
	    "name": "Unidad Prueba"
	}
	```
	
* ##### Error response:

	**Code:** 404 NOT FOUND

### Delete unit

* ##### URL:

	< /{id} >

* ##### Method:
	
	`DELETE`
	
* ##### URL Params:

	`id=[long]`
	
* ##### Example of query:

	* URL
		
		`/api/Unit/1`
		
* ##### Data Params:
	
	```
	{
		"name": "Unidad Prueba"
	}
	```

* ##### Success response:

	```
	{
	    "id": 1,
	    "name": "Unidad Prueba"
	}
	```
	
* ##### Error response:

	**Code:** 404 NOT FOUND


## Lesson
The following queries will be preceded by /Unit.

### Resource to show all the lessons

* ##### URL:

	< /Lessons/ >

* ##### Method:
	
	`GET`
	
* ##### URL Params:

	`Empty`
	
* ##### Example of query:

	* URL
		
		`/api/Unit/Lessons/`
		
* ##### Success response:

	```
	{
	    "totalElements": 6,
	    "totalPages": 1,
	    "number": 0,
	    "size": 20,
	    "first": true,
	    "last": true,
	    "content": [
		{
		    "id": 4,
		    "name": "Lección 1 Unidad 2"
		},
		{
		    "id": 5,
		    "name": "Lección 2 Unidad 2"
		},
		{
		    "id": 6,
		    "name": "Lección 3 Unidad 2"
		},
		{
		    "id": 7,
		    "name": "Lección 1 Unidad 3"
		},
		{
		    "id": 8,
		    "name": "Lección 2 Unidad 3"
		},
		{
		    "id": 9,
		    "name": "Lección 3 Unidad 3"
		}
	    ]
	}
	
	```

### Resource to show an unit with his lessons

* ##### URL:

	< /{idunit}/Lesson/ >

* ##### Method:
	
	`GET`
	
* ##### URL Params:

	`idunit=[long]`
	
* ##### Example of query:

	* URL
		
		`/api/Unit/1/Lesson/`
		
* ##### Success response:

	```
	{
	    "id": 1,
	    "name": "Unidad 1",
	    "lessons": [
		{
		    "id": 1,
		    "name": "Lección 1 Unidad 1",
		    "exercises": null
		},
		{
		    "id": 2,
		    "name": "Lección 2 Unidad 1",
		    "exercises": null
		},
		{
		    "id": 3,
		    "name": "Lección 3 Unidad 1",
		    "exercises": null
		}
	    ]
	}
	```
	
* ##### Error response:

	**Code:** 404 NOT FOUND

### Resource to show one lesson

* ##### URL:

	< /{idunit}/Lesson/{id} >

* ##### Method:
	
	`GET`
	
* ##### URL Params:

	`
	idunit=[long]
	id=[long]
	`
	
* ##### Example of query:

	* URL
		
		`/api/Unit/1/Lesson/1`
		
* ##### Success response:

	```
	{
	    "id": 1,
	    "name": "Lección 1 Unidad 1"
	}
	```
	
* ##### Error response:

	**Code:** 404 NOT FOUND

### Change the name of the lesson

* ##### URL:

	< /{idunit}/Lesson/{id} >

* ##### Method:
	
	`PUT`
	
* ##### URL Params:

	`
	idunit=[long]
	id=[long]
	`
	
* ##### Example of query:

	* URL
		
		`/api/Unit/1/Lesson/1`
		
* ##### Data Params:
	
	```
	{
		"name": "Leccion Prueba"
	}
	```

* ##### Success response:

	```
	{
	    "id": 1,
	    "name": "Leccion Prueba"
	}
	```
	
* ##### Error response:

	**Code:** 404 NOT FOUND


