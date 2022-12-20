import { Injectable, OnInit } from '@angular/core';
import { User } from '../Interfaces/User/user.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { UtilsService } from '../../../src/app/services/utils.service';
import 'rxjs/Rx';

const URL = environment.apiBase;


@Injectable()
export class LoginService {

    isLogged = false;
    isAdmin = false;
    user;

    constructor(private http: HttpClient,public utils: UtilsService) { }

    getLoggedUser() {
        return this.user;
    }
    setLoggedUser(user){
        console.log("Logged:" + this.isLogged);
        this.user = user;
    }

    isLoggedUser() {
        return this.isLogged;
    }

    isAdministrator() {
        return this.isAdmin;
    }

    reqIsLogged() {

        const headers = new HttpHeaders({
            'X-Requested-With': 'XMLHttpRequest'
        });

        this.http.get(URL + '/login', { withCredentials: true, headers }).subscribe(
            response => this.processLogInResponse(response),
            error => {
                if (error.status !== 401) {
                    console.error('Error when asking if logged: ' + JSON.stringify(error));
                }
            }
        );
    }

    private processLogInResponse(response) {
        this.isLogged = true;
        this.user = response;
        this.isAdmin = this.user.roles.indexOf('ROLE_ADMIN') !== -1;
    }

    async logIn(user: string, pass: string) {
        
        const userPass = user + ':' + pass;

        const headers = new HttpHeaders({
            'Authorization': 'Basic ' + utf8_to_b64(userPass),
            'X-Requested-With': 'XMLHttpRequest'
        });

        let useData = null;
			 await this.utils.restServiceHeaders('/login', {
				method: 'get',
                headers: headers 
			  }).toPromise().then(
				(data) => {
				  if (typeof data !== 'undefined' && data !== null) {
					this.processLogInResponse(data);
					useData = data;
				  }
				}
			  );
		return useData;

        /*const userPass = user + ':' + pass;

        const headers = new HttpHeaders({
            'Authorization': 'Basic ' + utf8_to_b64(userPass),
            'X-Requested-With': 'XMLHttpRequest'
        });


        return this.http.get(URL + '/login', { withCredentials: true, headers }).map(
            response => {
                this.processLogInResponse(response);
                return this.user;
            }
        );*/
    }

    async logOut() {
        let useData = null;
			 await this.utils.restService('/login', {
				method: 'get',
			  }).toPromise().then(
				(data) => {
				  if (typeof data !== 'undefined' && data !== null) {
					this.isLogged = false;
                    this.isAdmin = false;
					useData = data;
				  }
				}
			  );
		return useData;
    }
}

function utf8_to_b64(str) {
    return btoa(encodeURIComponent(str).replace(/%([0-9A-F]{2})/g, function (match, p1) {//Mirar si esta bien
        return String.fromCharCode(<any>'0x' + p1);
    }));
}