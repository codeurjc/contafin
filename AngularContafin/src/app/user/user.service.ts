import { Injectable } from '@angular/core';
import { Unit } from '../Interfaces/Unit/unit.model';
import { Http } from '@angular/http';


const BASE_URL = 'https://localhost:8080/api/User';

@Injectable()
export class UserService{

    constructor (private http: Http){

    }

    getUser(){
        let url = BASE_URL + "/Name";
        this.http.get(url).subscribe(
            response => {
                let user = response.json();
            },
            error => console.log(error)
        );
        console.log(name);
    }

    getUserName(){
        let url = BASE_URL + "/Name";
        this.http.get(url).subscribe(
            response => {
                let name = response.json();
            },
            error => console.log(error)
        );
        console.log(name);
    }

}