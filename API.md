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

**All API queries have been preceded by /api**

## Login
All methods linked to User will return the same answers except those indicated.

#### Resource login
Allows a user to log in.Allows a user to log in.

* ##### URL:

	< /login >

* ##### Method:

	`GET`
	
* ##### Success Response:
  
  	```
	{
	    "id": 2,
	    "name": "User1",
	    "email": "--------@hotmail.es",
	    "level": 1,
	    "points": 0,
	    "streak": 0,
	    "fluency": 0,
	    "dailyGoal": 0,
	    "lastConnection": "2018-03-19/11:44:21",
	    "lastUnit": 0,
	    "lastLesson": 0,
	    "progress": null,
	    "remainingGoals": 0,
	    "exp": 0
	}
	
	```
  * ##### Error Response:

	**Code**: 401 UNAUTHORIZED
	
#### resource loguot  
Allows a user to log out.

* ##### URL:

	< /logout >

* ##### Method:

	`GET`

* ##### Success Response:

	```
	true
	```

  * ##### Error Response:

	**Code**: 404 NOT FOUND

## Admin
These must be preceded by /Admin. All methods linked to Admin will return the same answers except those indicated.
  
### Obtain user data
Get a table with the different users and their data.

* ##### URL

	< /UserData >

* ##### Método:

	`GET`
  
* ##### Success Response:

  	```
	{
	    "totalElements": 44,
	    "totalPages": 3,
	    "number": 0,
	    "size": 10,
	    "first": true,
	    "last": false,
	    "content": [
		{
		    "id": 1,
		    "name": "user",
		    "email": "------@hotmail.es",
		    "level": 1,
		    "points": 0,
		    "streak": 0,
		    "fluency": 0,
		    "dailyGoal": 0,
		    "lastConnection": "2018-03-19/11:44:10",
		    "lastUnit": 0,
		    "lastLesson": 0,
		    "progress": null,
		    "remainingGoals": 0,
		    "exp": 0
		},
		{
		    "id": 2,
		    "name": "admin",
		    "email": "------@hotmail.es",
		    "level": 1,
		    "points": 0,
		    "streak": 0,
		    "fluency": 0,
		    "dailyGoal": 0,
		    "lastConnection": "2018-03-19/11:47:13",
		    "lastUnit": 0,
		    "lastLesson": 0,
		    "progress": null,
		    "remainingGoals": 0,
		    "exp": 0
		},
		{
		    "id": 3,
		    "name": "Sergio",
		    "email": "------@hotmail.es",
		    "level": 2,
		    "points": 50,
		    "streak": 7,
		    "fluency": 0,
		    "dailyGoal": 3,
		    "lastConnection": "-",
		    "lastUnit": 0,
		    "lastLesson": 0,
		    "progress": null,
		    "remainingGoals": 3,
		    "exp": 0
		},
		{
		    "id": 4,
		    "name": "user 0",
		    "email": "------@hotmail.es",
		    "level": 1,
		    "points": 0,
		    "streak": 0,
		    "fluency": 0,
		    "dailyGoal": 0,
		    "lastConnection": "-",
		    "lastUnit": 0,
		    "lastLesson": 0,
		    "progress": null,
		    "remainingGoals": 0,
		    "exp": 0
		},
		{
		    "id": 5,
		    "name": "user 1",
		    "email": "------@hotmail.es",
		    "level": 1,
		    "points": 0,
		    "streak": 0,
		    "fluency": 0,
		    "dailyGoal": 0,
		    "lastConnection": "-",
		    "lastUnit": 0,
		    "lastLesson": 0,
		    "progress": null,
		    "remainingGoals": 0,
		    "exp": 0
		},
		{
		    "id": 6,
		    "name": "user 2",
		    "email": "------@hotmail.es",
		    "level": 1,
		    "points": 0,
		    "streak": 0,
		    "fluency": 0,
		    "dailyGoal": 0,
		    "lastConnection": "-",
		    "lastUnit": 0,
		    "lastLesson": 0,
		    "progress": null,
		    "remainingGoals": 0,
		    "exp": 0
		},
		{
		    "id": 7,
		    "name": "user 3",
		    "email": "------@hotmail.es",
		    "level": 1,
		    "points": 0,
		    "streak": 0,
		    "fluency": 0,
		    "dailyGoal": 0,
		    "lastConnection": "-",
		    "lastUnit": 0,
		    "lastLesson": 0,
		    "progress": null,
		    "remainingGoals": 0,
		    "exp": 0
		},
		{
		    "id": 8,
		    "name": "user 4",
		    "email": "------@hotmail.es",
		    "level": 1,
		    "points": 0,
		    "streak": 0,
		    "fluency": 0,
		    "dailyGoal": 0,
		    "lastConnection": "-",
		    "lastUnit": 0,
		    "lastLesson": 0,
		    "progress": null,
		    "remainingGoals": 0,
		    "exp": 0
		},
		{
		    "id": 9,
		    "name": "user 5",
		    "email": "------@hotmail.es",
		    "level": 1,
		    "points": 0,
		    "streak": 0,
		    "fluency": 0,
		    "dailyGoal": 0,
		    "lastConnection": "-",
		    "lastUnit": 0,
		    "lastLesson": 0,
		    "progress": null,
		    "remainingGoals": 0,
		    "exp": 0
		},
		{
		    "id": 10,
		    "name": "user 6",
		    "email": "------@hotmail.es",
		    "level": 1,
		    "points": 0,
		    "streak": 0,
		    "fluency": 0,
		    "dailyGoal": 0,
		    "lastConnection": "-",
		    "lastUnit": 0,
		    "lastLesson": 0,
		    "progress": null,
		    "remainingGoals": 0,
		    "exp": 0
		}
	    ]
	}
  
* ##### Error Response:

	**Code**: 401 UNAUTHORIZED
  
#### Export data from all users

Export all users to an excel to analyze their data

* ##### URL

	< /UserData/Export >

* ##### Method:

	`GET`

* ##### Success Response:
	
	```
	FALTA ESTO
	```
	
* ##### Error Response:

	* Code: 403 FORBIDDEN

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
  
## Unit
The following queries will be preceded by /Unit.

### Resource to show all units

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


### Create Unit with its lessons and exercises

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

### Resource to show all lessons

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

### Resource to show an unit with its lessons

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
	
### Check if a lesson is completed

* ##### URL:

	< /{idunit}/Lesson/{idlesson}/Completed >

* ##### Method:
	
	`GET`
	
* ##### URL Params:

	`
	idunit=[long]
	idlesson=[long]
	`
	
* ##### Example of query:

	* URL
		
		`/api/Unit/1/Lesson/1/Completed`
		
* ##### Success response:

	```
	false
	```
	
* ##### Error response:

	**Code:** 404 NOT FOUND


## Exercise

The following queries must be preceded by /Unit.

### Resource to show all exercises

* ##### URL:

	< /Lesson/Exercises/ >

* ##### Method:

	`GET`
	
* ##### URL Params:

	`Empty`

* ##### Example of query:

	* URL
		
		`api/Unit/Lesson/Exercises/`

* ##### Success Response:

	```
	{
	    "totalElements": 24,
	    "totalPages": 2,
	    "number": 0,
	    "size": 10,
	    "first": true,
	    "last": false,
	    "content": [
		{
		    "id": 1,
		    "kind": 1,
		    "statement": "1.1.1 Seleccione el asiento",
		    "texts": [
			"213.Maquinaria",
			"210.Terrenos y bienes naturales",
			"218. Elementos de transporte"
		    ]
		},
		{
		    "id": 2,
		    "kind": 2,
		    "statement": "1.1.2 Escribe la denominación de la cuenta que recoge: maquinarias para el proceso productivo de la empresa",
		    "texts": []
		},
		{
		    "id": 3,
		    "kind": 5,
		    "statement": "1.1.5 Escoge la respuesta correcta para la cuenta: 210. Terrenos y bienes naturales",
		    "texts": [
			"Activo",
			"Pasivo",
			"Patrimonio neto"
		    ]
		},
		{
		    "id": 4,
		    "kind": 7,
		    "statement": "1.1.7 Escoge el enunciado correcto para el asiento: \"10 211. Construcciones a 174. Provedores de inmovilizado a l/p 10\"",
		    "texts": [
			" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años a través de la letra de cambio.",
			"La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años.",
			" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará en un plazo no superior a un año."
		    ]
		},
		{
		    "id": 5,
		    "kind": 1,
		    "statement": "1.1.1 Seleccione el asiento",
		    "texts": [
			"213.Maquinaria",
			"210.Terrenos y bienes naturales",
			"218. Elementos de transporte"
		    ]
		},
		{
		    "id": 6,
		    "kind": 2,
		    "statement": "1.2.2 Escribe la denominación de la cuenta que recoge: maquinarias para el proceso productivo de la empresa",
		    "texts": []
		},
		{
		    "id": 7,
		    "kind": 5,
		    "statement": "1.2.5 Escoge la respuesta correcta para la cuenta: 210. Terrenos y bienes naturales",
		    "texts": [
			"Activo",
			"Pasivo",
			"Patrimonio neto"
		    ]
		},
		{
		    "id": 8,
		    "kind": 7,
		    "statement": "1.2.7 Escoge el enunciado correcto para el asiento: \"10 211. Construcciones a 174. Provedores de inmovilizado a l/p 10\"",
		    "texts": [
			" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años a través de la letra de cambio.",
			"La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años.",
			" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará en un plazo no superior a un año."
		    ]
		},
		{
		    "id": 9,
		    "kind": 1,
		    "statement": "1.3.1 Seleccione el asiento",
		    "texts": [
			"213.Maquinaria",
			"210.Terrenos y bienes naturales",
			"218. Elementos de transporte"
		    ]
		},
		{
		    "id": 10,
		    "kind": 2,
		    "statement": "1.3.2 Escribe la denominación de la cuenta que recoge: maquinarias para el proceso productivo de la empresa",
		    "texts": []
		}
	    ]
	}
	```

* ##### Error Response:

	**Code:** 404 NOT FOUND

### Resource to show one exercise

* ##### URL:

	< {idunit}/Lesson/{idlesson}/Exercise/{id} >

* ##### Method:

	`GET`

* ##### URL Params:

	* `idunit=[long]`
	* `idlesson=[long]`
	* `id=[long]`

* ##### Example of query:

	* URL
		
		`api/Unit/1/Lesson/1/Exercise/1`

* ##### Success Response:

	```
	{
	    "id": 1,
	    "kind": 1,
	    "statement": "1.1.1 Seleccione el asiento",
	    "texts": [
		"213.Maquinaria",
		"210.Terrenos y bienes naturales",
		"218. Elementos de transporte"
	    ]
	}
	```

* ##### Error Response:

	**Code:** 404 NOT FOUND

### Change an exercise

* ##### URL:

	< {idunit}/Lesson/{idlesson}/Exercise/{id} >

* ##### Method:

	`PUT`

* ##### URL Params:

	* `idunit=[long]`
	* `idlesson=[long]`
	* `id=[long]`

* ##### Example of query:

	* URL
		
		`api/Unit/1/Lesson/1/Exercise/1`

* ##### Data Params:

	```
	{
	    "kind": 1,
	    "statement": "1.1.1 Seleccione el asiento",
	    "texts": [
	        "213.Maquinaria",
	        "210.Terrenos y bienes naturales",
	        "218. Elementos de transporte"
	    ]
	}
	```

* ##### Success Response:

	```
	{
	    "id": 2,
	    "kind": 1,
	    "statement": "1.1.1 Seleccione el asiento",
	    "texts": [
		"213.Maquinaria",
		"210.Terrenos y bienes naturales",
		"218. Elementos de transporte"
	    ]
	}
	```

* ##### Error Response:

	**Code:** 404 NOT FOUND

### Resource to show an answer

* ##### URL:

	< {idunit}/Lesson/{idlesson}/Exercise/{id}/Answer >

* ##### Method:

	`GET`

* ##### URL Params:

	* `idunit=[long]`
	* `idlesson=[long]`
	* `id=[long]`

* ##### Example of query:

	* URL
		
		`api/Unit/1/Lesson/1/Exercise/1/Answer`

* ##### Success Response:

	```
	{
	    "id": 1,
	    "result": "uno"
	}
	
	```

* ##### Error Response:

	**Code:** 404 NOT FOUND

### Change an answer

* ##### URL:

	< {idunit}/Lesson/{idlesson}/Exercise/{id}/Answer >

* ##### Method:

	`PUT`

* ##### URL Params:

	* `idunit=[long]`
	* `idlesson=[long]`
	* `id=[long]`

* ##### Example of query:

	* URL
		
		`api/Unit/1/Lesson/1/Exercise/1/Answer`

* ##### Data Params:

	```
	{
	    "result": "dos"
	}
	```

* ##### Success Response:

	```
	{
	    "id": 1,
	    "result": "dos"
	}
	
	```

* ##### Error Response:

	**Code:** 404 NOT FOUND

### Check if an answer is correct or not

* ##### URL:

	< {idunit}/Lesson/{idlesson}/Exercise/{id}/Solution >

* ##### Method:

	`PUT`

* ##### URL Params:

	* `idunit=[long]`
	* `idlesson=[long]`
	* `id=[long]`

* ##### Example of query:

	* URL
		
		`api/Unit/1/Lesson/1/Exercise/1/Solution`

* ##### Data Params:

	```
	{
	    "result": "dos"
	}
	```

* ##### Success Response:

	```
	true
	```

* ##### Error Response:

	**Code:** 404 NOT FOUND
