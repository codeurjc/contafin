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
